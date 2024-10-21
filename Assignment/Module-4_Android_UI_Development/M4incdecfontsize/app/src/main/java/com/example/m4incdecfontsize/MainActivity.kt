package com.example.m4incdecfontsize

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 9 => create an application to increate font size when plus button click and decrease when minus button click

class MainActivity : AppCompatActivity() {

    lateinit var txt:TextView
    lateinit var btn1:Button
    lateinit var btn2:Button
    var font = 16f

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
        btn1 = findViewById(R.id.btndec)
        btn2 = findViewById(R.id.btninc)


        btn1.setOnClickListener {

            if (font > 10f)
            {
                font -= 2f
                txt.textSize = font
            }
        }

        btn2.setOnClickListener {

            if (font < 40f)
            {
                font += 2f
                txt.textSize = font
            }

        }

    }

}