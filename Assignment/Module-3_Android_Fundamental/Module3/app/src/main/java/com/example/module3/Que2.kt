package com.example.module3

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Que2 : AppCompatActivity(), View.OnClickListener {

    //Q2 : Change screen background color on different button click event

    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button

    lateinit var img:ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_que2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btn1 = findViewById(R.id.btn11)
        btn2 = findViewById(R.id.btn12)
        btn3 = findViewById(R.id.btn13)
        btn4 = findViewById(R.id.btn14)

        img = findViewById(R.id.img1)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

    }



    override fun onClick(k: View?)
    {
        if (k ==  btn1)
        {
            img.setBackgroundColor(Color.RED)
        }
        if (k ==  btn2)
        {
            img.setBackgroundColor(Color.BLUE)

        }
        if (k ==  btn3)
        {
            img.setBackgroundColor(Color.YELLOW)

        }
        if (k ==  btn4)
        {
            var i = Intent(applicationContext, Que3::class.java)
            Toast.makeText(applicationContext, "Move To Second Screen", Toast.LENGTH_SHORT).show()
            startActivity(i)
        }

    }

}
