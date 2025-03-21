package com.example.bookmydoc.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.data.model.Patient
import com.example.bookmydoc.databinding.ActivityPatientRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class PatientRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Registering...")

        binding.btnRegister.setOnClickListener {
            registerPatient()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, PatientLoginActivity::class.java))
        }
    }

    private fun registerPatient() {
        val fullName = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(phone) ||
            TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            return
        }

        progressDialog.show()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val patient = Patient(userId, fullName, phone, email, address, password)

                    FirebaseDatabase.getInstance().getReference("Patients")
                        .child(userId)
                        .setValue(patient)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Registration Successful. Please log in.", Toast.LENGTH_SHORT).show()

                            auth.signOut()

                             val intent = Intent(this, PatientLoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Failed to save patient data", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


}