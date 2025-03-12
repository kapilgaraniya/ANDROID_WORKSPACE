package com.example.bookmydoc.ui.patient.appointments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmydoc.data.model.Appointment
import com.example.bookmydoc.databinding.ActivityViewAppointmentsBinding
import com.example.bookmydoc.ui.patient.adapter.AppointmentAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewAppointmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAppointmentsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var appointmentAdapter: AppointmentAdapter
    private val appointmentList = mutableListOf<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Appointments")

        setupRecyclerView()
        fetchAppointments()
    }

    private fun setupRecyclerView() {
        binding.recyclerAppointments.layoutManager = LinearLayoutManager(this)
        appointmentAdapter = AppointmentAdapter(appointmentList, ::cancelAppointment)
        binding.recyclerAppointments.adapter = appointmentAdapter
    }

    private fun fetchAppointments() {
        val userId = auth.currentUser?.uid ?: return
        databaseReference.orderByChild("userId").equalTo(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    appointmentList.clear()
                    for (data in snapshot.children) {
                        val appointment = data.getValue(Appointment::class.java)
                        if (appointment != null) {
                            appointmentList.add(appointment)
                        }
                    }
                    appointmentAdapter.notifyDataSetChanged()
                    binding.tvNoAppointments.visibility = if (appointmentList.isEmpty()) View.VISIBLE else View.GONE
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ViewAppointmentsActivity, "Failed to load appointments", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun cancelAppointment(appointment: Appointment) {
        databaseReference.child(appointment.appointmentId).removeValue()
            .addOnSuccessListener {
                val scheduleReference = FirebaseDatabase.getInstance()
                    .getReference("DoctorSchedules")
                    .child(appointment.doctorId)
                    .child(appointment.date)
                    .child(appointment.timeSlot)
                scheduleReference.removeValue()
                Toast.makeText(this, "Appointment canceled", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to cancel appointment", Toast.LENGTH_SHORT).show()
            }
    }
}
