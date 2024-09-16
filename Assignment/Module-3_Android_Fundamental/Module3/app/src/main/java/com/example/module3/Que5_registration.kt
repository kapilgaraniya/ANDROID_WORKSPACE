package com.example.module3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var btn:Button
lateinit var lbtn:TextView

class Que5_registration : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_que5_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn = findViewById(R.id.Regisnbtn)
        lbtn = findViewById(R.id.llink)

        btn.setOnClickListener{

            var i = Intent(applicationContext, Que5_login::class.java)
            startActivity(i)
        }

        lbtn.setOnClickListener{

            var i = Intent(applicationContext, Que5_login::class.java)
            startActivity(i)

        }

    }
}