package com.example.m4hidetextview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 6 => create an application to hide TextView when first button click and show when second button click

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button1: Button
    lateinit var button2: Button

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

        textView = findViewById(R.id.txt1)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)

        button1.setOnClickListener {

            textView.setVisibility(View.INVISIBLE)
        }

        button2.setOnClickListener {

            textView.setVisibility(View.VISIBLE)
        }

    }
}