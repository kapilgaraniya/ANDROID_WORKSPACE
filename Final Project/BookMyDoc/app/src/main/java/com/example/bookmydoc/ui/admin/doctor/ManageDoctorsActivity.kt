package com.example.bookmydoc.ui.admin.doctor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmydoc.data.Doctor
import com.example.bookmydoc.databinding.ActivityManageDoctorsBinding
import com.example.bookmydoc.ui.admin.dashboard.AdminDashboardActivity
import com.example.bookmydoc.ui.admin.doctor.adapter.DoctorAdapter
import com.google.firebase.database.*

class ManageDoctorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDoctorsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var doctorList: ArrayList<Doctor>
    private lateinit var adapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Doctors")
        doctorList = ArrayList()

        binding.recyclerViewDoctors.layoutManager = LinearLayoutManager(applicationContext)
        adapter = DoctorAdapter(this, doctorList, ::editDoctor)
        binding.recyclerViewDoctors.adapter = adapter

        binding.btnAddDoctor.setOnClickListener {
            startActivity(Intent(applicationContext, AddDoctorActivity::class.java))
        }

        fetchDoctors()
    }

    private fun fetchDoctors() {
        binding.progressBar.visibility = View.VISIBLE
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                doctorList.clear()
                for (doctorSnapshot in snapshot.children) {
                    val doctor = doctorSnapshot.getValue(Doctor::class.java)
                    if (doctor != null) {
                        doctorList.add(doctor)
                    }
                }
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun editDoctor(doctor: Doctor) {
        val intent = Intent(applicationContext, EditDoctorActivity::class.java)
        intent.putExtra("doctor", doctor)
        startActivity(intent)
    }

    fun refreshDoctorList() {
        doctorList.clear()
        fetchDoctors() // Call your method to load doctors again
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, AdminDashboardActivity::class.java))
    }

}
