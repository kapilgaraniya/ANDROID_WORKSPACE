package com.example.bookmydoc.ui.admin.patient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmydoc.data.model.Patient
import com.example.bookmydoc.databinding.ActivityManagePatientsBinding
import com.example.bookmydoc.ui.admin.patient.adapter.PatientAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ManagePatientsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManagePatientsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var patientList: ArrayList<Patient>
    private lateinit var adapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagePatientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Patients")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        patientList = ArrayList()
        adapter = PatientAdapter(patientList) { patient ->
            showDeleteConfirmationDialog(patient)
        }
        binding.recyclerView.adapter = adapter

        // Fetch patients from Firebase
        fetchPatients()
    }

    private fun fetchPatients() {
        binding.progressBar.visibility = View.VISIBLE

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                patientList.clear()
                if (snapshot.exists()) {
                    for (patientSnap in snapshot.children) {
                        val patient = patientSnap.getValue(Patient::class.java)
                        patient?.let { patientList.add(it) }
                    }
                    adapter.notifyDataSetChanged()
                }
                binding.progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@ManagePatientsActivity, "Failed to load patients", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDeleteConfirmationDialog(patient: Patient) {
        AlertDialog.Builder(this)
            .setTitle("Delete Patient")
            .setMessage("Are you sure you want to delete this patient?")
            .setPositiveButton("Delete") { _, _ -> deletePatient(patient) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deletePatient(patient: Patient) {
        databaseReference.child(patient.id).removeValue()
            .addOnSuccessListener {
                FirebaseAuth.getInstance().currentUser?.delete()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Failed to delete user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to delete patient", Toast.LENGTH_SHORT).show()
            }
    }
}