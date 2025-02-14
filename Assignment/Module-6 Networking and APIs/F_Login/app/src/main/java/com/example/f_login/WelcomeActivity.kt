package com.example.f_login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        if (user != null) {
            welcomeTextView.text = "Welcome, ${user.email}!"
        } else {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        logoutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
