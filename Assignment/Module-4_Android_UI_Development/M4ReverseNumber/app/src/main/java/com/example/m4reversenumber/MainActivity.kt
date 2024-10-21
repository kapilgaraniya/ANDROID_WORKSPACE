package com.example.m4reversenumber

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q.-1 -> Create an application to take input number from user and print its reverse number in TextView without button.

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var txt1:TextView

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

        edt1 = findViewById(R.id.edt1)
        txt1 = findViewById(R.id.txt1)


        edt1.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                // ------
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val inputtext = s.toString()
                if (inputtext.isEmpty())
                {
                    txt1.text = ""
                }

                else
                {
                    val reverseNumber = inputtext.reversed()
                    txt1.text = reverseNumber
                }
            }

            override fun afterTextChanged(s: Editable?)
            {
                //-------
            }
        })
    }
}