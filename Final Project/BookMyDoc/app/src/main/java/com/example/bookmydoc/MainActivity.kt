package com.example.bookmydoc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.ui.admin.dashboard.AdminDashboardActivity
import com.example.bookmydoc.ui.auth.PatientLoginActivity
import com.example.bookmydoc.ui.patient.dashboard.PatientDashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            database = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.uid)

            database.child("userType").get().addOnSuccessListener { snapshot ->
                val userType = snapshot.value.toString()

                if (userType == "Admin") {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))
                } else {
                    startActivity(Intent(this, PatientDashboardActivity::class.java))
                }
                finish()
            }.addOnFailureListener {
                startActivity(Intent(this, PatientLoginActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this, PatientLoginActivity::class.java))
            finish()
        }
    }
}
