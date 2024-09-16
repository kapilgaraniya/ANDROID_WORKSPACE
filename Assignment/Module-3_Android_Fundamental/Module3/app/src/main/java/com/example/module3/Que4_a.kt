package com.example.module3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Que4_a : AppCompatActivity() {

    // Q4 : Pass data from one screen to second screen

    lateinit var cb1:CheckBox
    lateinit var cb2:CheckBox
    lateinit var cb3:CheckBox
    lateinit var cb4:CheckBox
    lateinit var cb5:CheckBox
    lateinit var cb6:CheckBox
    lateinit var cb7:CheckBox
    lateinit var cb8:CheckBox
    lateinit var cb9:CheckBox
    lateinit var cb10:CheckBox

    lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_que4_a)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cb1 = findViewById(R.id.ch1)
        cb2 = findViewById(R.id.ch2)
        cb3 = findViewById(R.id.ch3)
        cb4 = findViewById(R.id.ch4)
        cb5 = findViewById(R.id.ch5)
        cb6 = findViewById(R.id.ch6)
        cb7 = findViewById(R.id.ch7)
        cb8 = findViewById(R.id.ch8)
        cb9 = findViewById(R.id.ch9)
        cb10 = findViewById(R.id.ch10)

        btn = findViewById(R.id.btn1)

        btn.setOnClickListener {

            var builder = StringBuilder("\n Selected items")

            if (cb1.isChecked)
            {
                builder.append("\n1 One")
            }

            if (cb2.isChecked)
            {
                builder.append("\n2 Two")
            }

            if (cb3.isChecked)
            {
                builder.append("\n3 Three")
            }

            if (cb4.isChecked)
            {
                builder.append("\n4 Four")
            }

            if (cb5.isChecked)
            {
                builder.append("\n5 Five")
            }

            if (cb6.isChecked)
            {
                builder.append("\n6 Six")
            }

            if (cb7.isChecked)
            {
                builder.append("\n7 seven")
            }

            if (cb8.isChecked)
            {
                builder.append("\n8 Eight")
            }

            if (cb9.isChecked)
            {
                builder.append("\n9 nine")
            }

            if (cb10.isChecked)
            {
                builder.append("\n10 Ten")
            }

            Toast.makeText(applicationContext, "Pass data from one screen to second screen", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext, Que4_b::class.java)
            i.putExtra("k",builder.toString())
            startActivity(i)

        }
    }
}