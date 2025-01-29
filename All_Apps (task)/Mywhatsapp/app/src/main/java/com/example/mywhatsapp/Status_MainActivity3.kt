package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class Status_MainActivity3 : AppCompatActivity() {

    lateinit var img_s:ImageView
    lateinit var txt1_s:TextView
    lateinit var txt2_s:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_status_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        img_s = findViewById(R.id.imgstatus)
        txt1_s = findViewById(R.id.txt1status)
        txt2_s = findViewById(R.id.txt2status)

        var i = intent
        img_s.setImageResource(i.getIntExtra("i",1))
        txt1_s.setText(i.getStringExtra("t1",))
        txt2_s.setText(i.getStringExtra("t2",))

        var pos = i.getIntExtra("pos",1)
    }
}