package com.example.loginregistration

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q.-4 => Write a code to display login form when click on login button and registration form when click on registration button on single activity using fragment

class MainActivity : AppCompatActivity() {

    lateinit var btn1:Button
    lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1 = findViewById(R.id.btnlogin)
        btn2 = findViewById(R.id.btnregistration)

        btn1.setOnClickListener {

            var f1 = LoginFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.frmid, f1).commit()
        }

        btn2.setOnClickListener {

            var f2 = RegistrationFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.frmid, f2).commit()
        }

    }
}