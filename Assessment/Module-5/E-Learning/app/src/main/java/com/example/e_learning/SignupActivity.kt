package com.example.e_learning

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    lateinit var edtFirstName: EditText
    lateinit var edtLastName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtConfirmPassword: EditText
    lateinit var btnSignup: Button
    lateinit var tvLogin: TextView
    lateinit var sharedPreferences: SharedPreferences

    lateinit var dbHelper: DatabaseHelper

    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        edtFirstName = findViewById(R.id.firstname)
        edtLastName = findViewById(R.id.lastname)
        edtEmail = findViewById(R.id.Email)
        edtPassword = findViewById(R.id.Password)
        edtConfirmPassword = findViewById(R.id.confirmPassword)
        btnSignup = findViewById(R.id.btnsignup)
        tvLogin = findViewById(R.id.btn_login)

        sharedPreferences = getSharedPreferences("kapil", MODE_PRIVATE)
        dbHelper = DatabaseHelper(this)

        edtPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (edtPassword.right - edtPassword.compoundDrawables[drawableEnd].bounds.width())) {
                    togglePasswordVisibility()
                }
            }
            false
        }

        btnSignup.setOnClickListener {
            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val confirmPassword = edtConfirmPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                validatePassword(password)
            } catch (e: IllegalArgumentException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isInserted = dbHelper.insertUser(firstName, lastName, email, password)

            if (isInserted) {
                Toast.makeText(applicationContext, "Signup Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Signup Failed: Email may already exist", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            edtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.baseline_lock_24,
                0,
                R.drawable.baseline_remove_red_eye_24,
                0
            )
        }
        else{
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            edtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.baseline_lock_24,
                0,
                R.drawable.baseline_visibility_off_24,
                0
            )
        }

        edtPassword.setSelection(edtPassword.text.length)
        isPasswordVisible = !isPasswordVisible
    }

    private fun validatePassword(pwd: String) {
        require(pwd.length >= 8) { "Password must be at least 8 characters long." }
        require(pwd.none { it.isWhitespace() }) { "Password must not contain spaces." }
        require(pwd.any { it.isDigit() }) { "Password must contain at least one digit." }
        require(pwd.any { it.isUpperCase() }) { "Password must contain at least one uppercase letter." }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}