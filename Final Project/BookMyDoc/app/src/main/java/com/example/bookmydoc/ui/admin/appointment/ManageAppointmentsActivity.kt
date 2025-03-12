package com.example.bookmydoc.ui.admin.appointment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmydoc.R
import com.example.bookmydoc.data.model.Appointment
import com.example.bookmydoc.ui.admin.appointment.adapter.AdminAppointmentAdapter
import com.google.firebase.database.*

class ManageAppointmentsActivity : AppCompatActivity() {

    private lateinit var recyclerAppointments: RecyclerView
    private lateinit var appointmentAdapter: AdminAppointmentAdapter
    private lateinit var appointmentList: ArrayList<Appointment>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var userRef: DatabaseReference
    val SMS_PERMISSION_CODE = 101

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_appointments)

        recyclerAppointments = findViewById(R.id.recyclerAppointments)
        recyclerAppointments.layoutManager = LinearLayoutManager(this)
        appointmentList = ArrayList()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
        }

        appointmentAdapter = AdminAppointmentAdapter(this, appointmentList) { appointment ->
            cancelAppointment(appointment)
        }

        recyclerAppointments.adapter = appointmentAdapter

        databaseRef = FirebaseDatabase.getInstance().getReference("Appointments")
        userRef = FirebaseDatabase.getInstance().getReference("Patients")

        fetchAppointments()
    }

    private fun fetchAppointments() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                appointmentList.clear()
                for (appointmentSnapshot in snapshot.children) {
                    val appointment = appointmentSnapshot.getValue(Appointment::class.java)
                    if (appointment != null) {
                        fetchPatientDetails(appointment)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load appointments", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchPatientDetails(appointment: Appointment) {
        userRef.child(appointment.userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val patientId = snapshot.child("id").getValue(String::class.java) ?: "0000"
                val patientName = snapshot.child("fullName").getValue(String::class.java) ?: "Unknown"
                val patientContact = snapshot.child("phone").getValue(String::class.java) ?: "N/A"

                val updatedAppointment = appointment.copy(
                    userId = patientId,
                    patientName = patientName,
                    patientContact = patientContact
                )

                appointmentList.add(updatedAppointment)
                appointmentAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load patient details", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cancelAppointment(appointment: Appointment) {
        databaseRef.child(appointment.appointmentId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Appointment cancelled successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(applicationContext, "Failed to cancel appointment", Toast.LENGTH_SHORT).show()
            }
    }
}
