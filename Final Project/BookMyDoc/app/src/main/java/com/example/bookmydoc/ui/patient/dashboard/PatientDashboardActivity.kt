package com.example.bookmydoc.ui.patient.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmydoc.databinding.ActivityPatientDashboardBinding
import com.bookmydoc.data.Doctor
import com.example.bookmydoc.ui.patient.adapter.PatientDoctorAdapter
import com.google.firebase.database.*
import com.example.bookmydoc.R
import com.example.bookmydoc.ui.auth.PatientLoginActivity
import com.example.bookmydoc.ui.patient.appointments.BookAppointmentActivity
import com.example.bookmydoc.ui.patient.profile.PatientProfileActivity
import com.google.firebase.auth.FirebaseAuth

class PatientDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientDashboardBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var doctorList: MutableList<Doctor>
    private lateinit var doctorAdapter: PatientDoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            startActivity(Intent(this, PatientLoginActivity::class.java))
            finish()
            return
        }

        setSupportActionBar(binding.toolbar)

        doctorList = mutableListOf()
        doctorAdapter = PatientDoctorAdapter(this, doctorList,
            onDoctorClick = { selectedDoctor ->

                val intent = Intent(this, DoctorDetailsActivity::class.java).apply {
                    putExtra("doctorId", selectedDoctor.id)
                    putExtra("doctorName", selectedDoctor.name)
                    putExtra("doctorSpeciality", selectedDoctor.speciality)
                    putExtra("doctorExperience", selectedDoctor.experience)
                    putExtra("doctorEmail", selectedDoctor.email)
                    putExtra("doctorImage", selectedDoctor.imageUrl)
                    putExtra("doctorAverageRating", selectedDoctor.averageRating)
                }
                startActivity(intent)
            },
            onBookClick = { selectedDoctor ->

                val intent = Intent(this, BookAppointmentActivity::class.java).apply {
                    putExtra("doctorId", selectedDoctor.id)
                    putExtra("doctorName", selectedDoctor.name)
                }
                startActivity(intent)
            }
        )

        binding.recyclerViewDoctors.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDoctors.adapter = doctorAdapter

        fetchDoctors()
    }

    private fun fetchDoctors() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Doctors")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                doctorList.clear()
                for (doctorSnapshot in snapshot.children) {
                    val doctor = doctorSnapshot.getValue(Doctor::class.java)
                    doctor?.let {
                        fetchDoctorRating(it.id) { avgRating ->
                            it.averageRating = avgRating
                            doctorList.add(it)
                            doctorAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@PatientDashboardActivity,
                    "Failed to load doctors: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun fetchDoctorRating(doctorId: String, callback: (Float) -> Unit) {
        val ratingRef = FirebaseDatabase.getInstance().getReference("DoctorRatings").child(doctorId)

        ratingRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalRating = 0f
                var ratingCount = 0

                for (ratingSnapshot in snapshot.children) {
                    val rating = ratingSnapshot.getValue(Float::class.java) ?: 0f
                    totalRating += rating
                    ratingCount++
                }

                val averageRating = if (ratingCount > 0) totalRating / ratingCount else 0f
                callback(averageRating)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(0f)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_patient_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_contact_us -> {
                Toast.makeText(this, "Contact Us Clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_about_us -> {
                Toast.makeText(this, "About Us Clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_profile -> {
                startActivity(Intent(this, PatientProfileActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
