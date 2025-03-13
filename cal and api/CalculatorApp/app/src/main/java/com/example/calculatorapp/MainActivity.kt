package com.example.calculatorapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var answer: TextView
    private lateinit var calculateBtn: Button
    private lateinit var historyBtn: Button
    private lateinit var dbHelper: DBHelper
    private lateinit var operationSpinner: Spinner

    private val operations = arrayOf("+", "-", "*", "/")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1 = findViewById(R.id.etNumber1)
        number2 = findViewById(R.id.etNumber2)
        answer = findViewById(R.id.tvAnswer)
        calculateBtn = findViewById(R.id.btnCalculate)
        historyBtn = findViewById(R.id.btnHistory)
        operationSpinner = findViewById(R.id.spOperation)
        dbHelper = DBHelper(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)
        operationSpinner.adapter = adapter

        calculateBtn.setOnClickListener {
            calculate()
        }

        historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculate() {
        val num1 = number1.text.toString().toDoubleOrNull()
        val num2 = number2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedOperation = operationSpinner.selectedItem.toString()
        val result = when (selectedOperation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else "Cannot divide by zero"
            else -> 0
        }

        answer.text = result.toString()

        val calculation = "$num1 $selectedOperation $num2 = $result"
        dbHelper.insertHistory(calculation)
    }
}
