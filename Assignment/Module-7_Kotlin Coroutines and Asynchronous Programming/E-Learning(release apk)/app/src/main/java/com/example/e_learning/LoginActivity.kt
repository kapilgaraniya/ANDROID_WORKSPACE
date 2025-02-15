package com.example.e_learning

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.selects.select

class LoginActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvSignup: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var dbHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.etEmail)
        edtPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignup = findViewById(R.id.btnSignup)


        sharedPreferences = getSharedPreferences("kapil", MODE_PRIVATE)

        if (sharedPreferences.getBoolean("kapil", false) && !sharedPreferences.getString("n1", "").isNullOrEmpty()) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }


        dbHelper = DatabaseHelper(this)

        btnLogin.setOnClickListener {
            var email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.checkUser( email, password)) {

                var editor = sharedPreferences.edit()
                editor.putBoolean("kapil", true)
                editor.putString("n1", email)
                editor.apply()

                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
            else
            {
                Toast.makeText(applicationContext, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
            }
        }

        tvSignup.setOnClickListener {
            startActivity(Intent(applicationContext, SignupActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}