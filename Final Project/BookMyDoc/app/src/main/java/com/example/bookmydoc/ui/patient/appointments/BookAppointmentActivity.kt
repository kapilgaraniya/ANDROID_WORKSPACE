package com.example.bookmydoc.ui.patient.appointments

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.R
import com.example.bookmydoc.data.model.Appointment
import com.example.bookmydoc.data.model.Patient
import com.example.bookmydoc.databinding.ActivityBookAppointmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookAppointmentBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var scheduleReference: DatabaseReference

    private val predefinedTimeSlots = listOf("10:00", "11:00", "12:00", "02:00", "03:00", "05:00", "06:00")
    private val availableSlots = mutableListOf<String>()

    private var selectedTime: String? = null
    private var selectedDate: String? = null
    private var doctorId: String = ""
    private var doctorName: String = ""
    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Appointments")

        doctorId = intent.getStringExtra("doctorId") ?: ""
        doctorName = intent.getStringExtra("doctorName") ?: "Unknown"
        userId = auth.currentUser?.uid ?: ""

        binding.tvDoctorName.text = doctorName

        fetchDoctorImage()

        binding.tvSelectedDate.setOnClickListener {
            openDatePicker()
        }

        binding.btnConfirmAppointment.setOnClickListener {
            fetchPatientDetailsAndBook() // ✅ Fetch patient details before booking
        }

    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                selectedDate = formattedDate
                binding.tvSelectedDate.text = formattedDate
                fetchAvailableSlots(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
    }

    private fun fetchAvailableSlots(selectedDate: String) {
        scheduleReference = FirebaseDatabase.getInstance().getReference("DoctorSchedules").child(doctorId)
        scheduleReference.child(selectedDate).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                availableSlots.clear()
                for (slot in predefinedTimeSlots) {
                    if (!snapshot.hasChild(slot)) {
                        availableSlots.add(slot)
                    }
                }
                setupTimeSpinner()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun setupTimeSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, availableSlots)
        binding.spinnerTime.adapter = adapter
        binding.spinnerTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedTime = availableSlots[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // Fetch patient details and then book the appointment
    private fun fetchPatientDetailsAndBook() {
        val patientReference = FirebaseDatabase.getInstance().getReference("Patients").child(userId)

        patientReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val patient = snapshot.getValue(Patient::class.java)
                if (patient != null) {
                    if (selectedDate != null && selectedTime != null) {
                        bookAppointment(patient, doctorId, doctorName, selectedDate!!, selectedTime!!)
                    } else {
                        Toast.makeText(this@BookAppointmentActivity, "Please select a date and time", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@BookAppointmentActivity, "Failed to fetch patient details", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BookAppointmentActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // ✅ Book appointment with fetched patient data
    private fun bookAppointment(patient: Patient, doctorId: String, doctorName: String, date: String, timeSlot: String) {
        val appointmentId = databaseReference.push().key ?: return  // Generate unique ID

        val appointment = Appointment(
            appointmentId = appointmentId,
            date = date,
            doctorId = doctorId,
            doctorName = doctorName,
            patientName = patient.fullName,
            patientContact = patient.phone,
            timeSlot = timeSlot,
            userId = patient.id
        )

        // Save appointment to Firebase
        databaseReference.child(appointmentId).setValue(appointment)
            .addOnSuccessListener {
                Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Failed to book appointment: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun fetchDoctorImage() {
        val doctorImageReference = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("imageUrl")
        doctorImageReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val imageBase64 = snapshot.getValue(String::class.java)
                if (!imageBase64.isNullOrEmpty()) {
                    binding.ivDoctorImage.setImageBitmap(decodeBase64ToBitmap(imageBase64))
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }
}
