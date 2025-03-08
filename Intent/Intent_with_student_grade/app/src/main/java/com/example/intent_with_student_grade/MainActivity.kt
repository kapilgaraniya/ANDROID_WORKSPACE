package com.example.intent_with_student_grade

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var st_standard: EditText
    lateinit var st_name: EditText
    lateinit var st_roll: EditText
    lateinit var sub1: EditText
    lateinit var sub2: EditText
    lateinit var sub3: EditText
    lateinit var sub4: EditText
    lateinit var sub5: EditText
    lateinit var submit: Button

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

        st_standard = findViewById(R.id.edtStandard)
        st_name = findViewById(R.id.edtname)
        st_roll = findViewById(R.id.edtRollno)
        sub1 = findViewById(R.id.edtmark1)
        sub2 = findViewById(R.id.edtmark2)
        sub3 = findViewById(R.id.edtmark3)
        sub4 = findViewById(R.id.edtmark4)
        sub5 = findViewById(R.id.edtmark5)
        submit = findViewById(R.id.submitbtn)

        submit.setOnClickListener {

            val s1 = sub1.text.toString().toIntOrNull() ?: 0
            val s2 = sub2.text.toString().toIntOrNull() ?: 0
            val s3 = sub3.text.toString().toIntOrNull() ?: 0
            val s4 = sub4.text.toString().toIntOrNull() ?: 0
            val s5 = sub5.text.toString().toIntOrNull() ?: 0

            val marktotal = s1 + s2 + s3 + s4 + s5
            val percentage = (marktotal.toDouble() / 500) * 100  // Correct percentage formula

            val studentGrade = when {
                percentage >= 90 -> "A+"
                percentage >= 80 -> "A"
                percentage >= 70 -> "B"
                percentage >= 60 -> "C"
                percentage >= 50 -> "D"
                else -> "Fail!!"
            }

            // Correctly fetch text values
            val standard = st_standard.text.toString()
            val sname = st_name.text.toString()

            // Creating intent and passing data
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("standard", standard)
            i.putExtra("name", sname)
            i.putExtra("total", marktotal.toString())
            i.putExtra("percentage", "%.2f".format(percentage))
            i.putExtra("grade", studentGrade)

            startActivity(i)
        }
    }
}
