package com.example.bookmydoc.ui.patient.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.bookmydoc.R
import com.example.bookmydoc.ui.auth.PatientLoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val sharedPreferences = getSharedPreferences("STORAGE", MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(
            if (sharedPreferences.getBoolean("IS_DARKMODE_ENABLED", false))
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val userType = sharedPreferences.getString("USER_TYPE", "NON")?.trim()
            val intent = when {
                FirebaseAuth.getInstance().currentUser != null && userType == "PATIENT" ->
                    Intent(this, PatientLoginActivity::class.java)
                FirebaseAuth.getInstance().currentUser != null && userType == "DOCTOR" ->
                    Intent(this, PatientLoginActivity::class.java)
                else -> Intent(this, PatientLoginActivity::class.java)
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }
}
