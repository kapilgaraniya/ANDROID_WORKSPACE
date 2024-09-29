package com.example.mywhatsapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var img:ImageView
    lateinit var txt1:TextView
    lateinit var txt4:TextView
    lateinit var txt5:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        img = findViewById(R.id.img)
        txt1 = findViewById(R.id.txt1)
        txt4 = findViewById(R.id.txt2)
        txt5 = findViewById(R.id.txt3)

        var i = intent

        img.setImageResource(i.getIntExtra("i",101))
        txt1.setText(i.getStringExtra("n"))
        txt4.setText(i.getStringExtra("p"))
        txt5.setText(i.getStringExtra("c"))

        var pos = i.getIntExtra("pos",101)

    }
}