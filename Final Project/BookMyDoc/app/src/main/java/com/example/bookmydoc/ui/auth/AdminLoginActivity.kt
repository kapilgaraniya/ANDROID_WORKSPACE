package com.example.bookmydoc.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.databinding.ActivityAdminLoginBinding
import com.example.bookmydoc.ui.admin.dashboard.AdminDashboardActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)

        // Check if Admin is already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToDashboard()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email == "admin@gmail.com" && password == "admin123") {
                saveAdminSession()
                navigateToDashboard()
            } else {
                Toast.makeText(this, "Only Admins can log in!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Save Admin Login in SharedPreferences
    private fun saveAdminSession() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("adminEmail", "admin@gmail.com")
        editor.putString("adminPassword", "admin123")
        editor.apply()
    }

    private fun navigateToDashboard() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, AdminDashboardActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, PatientLoginActivity::class.java))
        finish()
    }
}
