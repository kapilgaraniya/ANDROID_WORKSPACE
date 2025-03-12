package com.example.bookmydoc.ui.patient.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.databinding.ActivityPatientProfileBinding
import com.example.bookmydoc.databinding.DialogUpdateProfileBinding
import com.example.bookmydoc.ui.auth.PatientLoginActivity
import com.example.bookmydoc.ui.patient.appointments.BookAppointmentActivity
import com.example.bookmydoc.ui.patient.appointments.ViewAppointmentsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PatientProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid ?: return
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients").child(userId)

        fetchUserProfile()


        binding.btnUpdateProfile.setOnClickListener {
            showUpdateDialog()
        }

        binding.btnViewAppointments.setOnClickListener {

            startActivity(Intent(applicationContext, ViewAppointmentsActivity::class.java))

        }

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, PatientLoginActivity::class.java))
            finish()
        }
    }

    private fun fetchUserProfile() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val fullName = snapshot.child("fullName").getValue(String::class.java) ?: "N/A"
                    val email = snapshot.child("email").getValue(String::class.java) ?: "N/A"
                    val phone = snapshot.child("phone").getValue(String::class.java) ?: "N/A"
                    val address = snapshot.child("address").getValue(String::class.java) ?: "N/A"

                    binding.tvFullName.text = fullName
                    binding.tvEmail.text = email
                    binding.tvPhone.text = phone
                    binding.tvAddress.text = address
                } else {
                    Toast.makeText(this@PatientProfileActivity, "Profile data not found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PatientProfileActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showUpdateDialog() {
        val dialogBinding = DialogUpdateProfileBinding.inflate(LayoutInflater.from(this))
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        dialog.setOnShowListener {
            val window = dialog.window
            if (window != null) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(window.attributes)
                layoutParams.width = (resources.displayMetrics.widthPixels * 0.9).toInt()
                window.attributes = layoutParams
            }
        }

        dialogBinding.etFullName.setText(binding.tvFullName.text.toString())
        dialogBinding.etPhone.setText(binding.tvPhone.text.toString())
        dialogBinding.etAddress.setText(binding.tvAddress.text.toString())
        dialogBinding.etPassword.setText(binding.tvPassword.text.toString())

        dialogBinding.btnUpdate.setOnClickListener {
            val newFullName = dialogBinding.etFullName.text.toString().trim()
            val newPhone = dialogBinding.etPhone.text.toString().trim()
            val newAddress = dialogBinding.etAddress.text.toString().trim()
            val newPassword = dialogBinding.etPassword.text.toString().trim()

            if (newFullName.isEmpty() || newPhone.isEmpty() || newAddress.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updateMap = mapOf(
                "fullName" to newFullName,
                "phone" to newPhone,
                "address" to newAddress,
                "password" to newPassword
            )

            databaseReference.updateChildren(updateMap).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
                    fetchUserProfile() // Refresh the profile data
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }
}
