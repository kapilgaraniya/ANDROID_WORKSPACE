package com.example.f_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var emailedt: EditText
    lateinit var passedt: EditText
    lateinit var loginbtn: Button
    lateinit var signupbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        emailedt = findViewById(R.id.emailEditText)
        passedt = findViewById(R.id.passwordEditText)
        loginbtn = findViewById(R.id.loginButton)
        signupbtn = findViewById(R.id.signupButton)

        loginbtn.setOnClickListener {
            var email = emailedt.text.toString()
            var password = passedt.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, WelcomeActivity::class.java))
                        finish()

                    }
                    else {

                        Toast.makeText(applicationContext, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        signupbtn.setOnClickListener {

            startActivity(Intent(applicationContext, SignupActivity::class.java))
        }
    }
}
