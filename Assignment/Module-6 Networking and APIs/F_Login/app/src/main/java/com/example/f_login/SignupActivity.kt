package com.example.f_login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var nameedt: EditText
    lateinit var emailedt: EditText
    lateinit var passedt: EditText
    lateinit var signupbtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        nameedt = findViewById(R.id.nameEditText)
        emailedt = findViewById(R.id.emailEditText)
        passedt = findViewById(R.id.passwordEditText)
        signupbtn = findViewById(R.id.signupButton)

        signupbtn.setOnClickListener {

            var name = nameedt.text.toString()
            var email = emailedt.text.toString()
            var password = passedt.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
