package com.example.intent_with_student_grade

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var standard: TextView
    lateinit var name: TextView
    lateinit var total: TextView
    lateinit var percentage: TextView
    lateinit var grade: TextView
    lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        standard = findViewById(R.id.Standard)
        name = findViewById(R.id.Name)
        total = findViewById(R.id.Total)
        percentage = findViewById(R.id.Percentage)
        grade = findViewById(R.id.Grade)
        back = findViewById(R.id.backbtn)

        // Get values from intent
        standard.text = "Standard: " + (intent.getStringExtra("standard") ?: "N/A")
        name.text = "Name: " + (intent.getStringExtra("name") ?: "N/A")
        total.text = "Total Marks: " + (intent.getStringExtra("total") ?: "0")
        percentage.text = "Percentage: " + (intent.getStringExtra("percentage") ?: "0.0") + "%"
        grade.text = "Grade: " + (intent.getStringExtra("grade") ?: "N/A")

        back.setOnClickListener {

            finish()
        }
    }
}
