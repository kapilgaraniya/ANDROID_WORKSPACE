package com.example.m4calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt
import kotlin.time.times

// Q-3 => Create an application with radio buttons (Add, Subtraction, Multiply, Division) EditText (number1, number2) and print result as per user choice  from radio button in TextView

class MainActivity : AppCompatActivity() {

    lateinit var EditText1: EditText
    lateinit var EditText2: EditText
    lateinit var radioGroup: RadioGroup
    lateinit var TextView: TextView


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

        EditText1 = findViewById(R.id.edt1)
        EditText2 = findViewById(R.id.edt2)
        radioGroup = findViewById(R.id.rg)
        TextView = findViewById(R.id.result)


        radioGroup.setOnCheckedChangeListener {_, checkedId ->

            val number1 = EditText1.text.toString().toDoubleOrNull()
            val number2 = EditText2.text.toString().toDoubleOrNull()

            val checke = radioGroup.checkedRadioButtonId
            var result: Double? = null

            when (checke) {
                R.id.add -> result = number1!! + number2!!
                R.id.sub -> result = number1!! - number2!!
                R.id.mul -> result = number1!! * number2!!
                R.id.div -> result = number1!! / number2!!
            }

            TextView.text = "Result: $result"
        }
    }
}