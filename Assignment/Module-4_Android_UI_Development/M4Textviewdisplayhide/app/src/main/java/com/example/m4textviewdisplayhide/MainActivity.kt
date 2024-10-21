package com.example.m4textviewdisplayhide

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 11 => Create an application to display Textview when checkbox is checked and
//         hide otherwise A6 Write a program to show four images around Textview.

class MainActivity : AppCompatActivity() {

    lateinit var txt:TextView
    lateinit var cb:CheckBox
    lateinit var btn:Button

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

        txt = findViewById(R.id.txt)
        cb = findViewById(R.id.cb)
        btn = findViewById(R.id.btn)

        cb.setOnCheckedChangeListener{view, isChecked ->

            txt.visibility = when (isChecked) {
                true -> android.view.View.GONE
                false -> android.view.View.VISIBLE
            }
        }

        btn.setOnClickListener {

            Toast.makeText(applicationContext, "Write a program to show four images around Textview", Toast.LENGTH_SHORT).show()

            var i = Intent(applicationContext, MainActivity2::class.java)
            startActivity(i)

        }

    }
}