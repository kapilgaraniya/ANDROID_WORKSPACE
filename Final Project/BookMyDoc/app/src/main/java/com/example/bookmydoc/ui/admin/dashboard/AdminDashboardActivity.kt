package com.example.bookmydoc.ui.admin.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.databinding.ActivityAdminDashboardBinding
import com.example.bookmydoc.ui.admin.appointment.ManageAppointmentsActivity
import com.example.bookmydoc.ui.admin.patient.ManagePatientsActivity
import com.example.bookmydoc.ui.admin.doctor.ManageDoctorsActivity
import com.example.bookmydoc.ui.auth.AdminLoginActivity
import com.example.bookmydoc.ui.auth.PatientLoginActivity

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE)

        binding.cardManageDoctors.setOnClickListener {
            startActivity(Intent(this, ManageDoctorsActivity::class.java))
        }

        binding.cardManagePatients.setOnClickListener {
            startActivity(Intent(this, ManagePatientsActivity::class.java))
        }

        binding.cardManageAppointments.setOnClickListener {
             startActivity(Intent(this, ManageAppointmentsActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            logoutAdmin()
        }
    }

    private fun logoutAdmin() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(applicationContext, PatientLoginActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
