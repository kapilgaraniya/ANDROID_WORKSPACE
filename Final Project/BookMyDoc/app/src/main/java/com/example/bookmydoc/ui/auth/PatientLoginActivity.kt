package com.example.bookmydoc.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.databinding.ActivityPatientLoginBinding
import com.example.bookmydoc.ui.patient.dashboard.PatientDashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class PatientLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Logging in...")

        if (auth.currentUser != null) {
            startActivity(Intent(this, PatientDashboardActivity::class.java))
            finish() // Close login activity
        }

        binding.btnLogin.setOnClickListener {
            loginPatient()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, PatientRegisterActivity::class.java))
        }
        binding.tvAdminLogin.setOnClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }

    }

    private fun loginPatient() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            return
        }

        progressDialog.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    val userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)

                    userRef.child("userType").setValue("Patient").addOnCompleteListener {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, PatientDashboardActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, PatientDashboardActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
