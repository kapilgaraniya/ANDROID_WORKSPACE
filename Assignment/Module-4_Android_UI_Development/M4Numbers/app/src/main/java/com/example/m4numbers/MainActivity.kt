package com.example.m4numbers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q-2 -> Create an application to input 2 numbers from user and all numbers between those 2 numbers in next activity

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            var fn1 = edt1.text.toString()
            var sn2 = edt2.text.toString()

            var fn = fn1.toInt()
            var sn = sn2.toInt()

            fn += 1
            sn -= 1


            var builder = StringBuilder("")

            for (x in fn..sn)
            {
                builder.append("\n-> ",x)
                var i = Intent(applicationContext, MainActivity2::class.java)
                i.putExtra("k",builder.toString())
                startActivity(i)

            }

        }

    }
}