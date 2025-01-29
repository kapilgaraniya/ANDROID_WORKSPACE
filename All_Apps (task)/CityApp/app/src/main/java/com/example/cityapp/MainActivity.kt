package com.example.cityapp

import android.os.Bundle
import android.view.Display.Mode
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var list:GridView
    lateinit var sp:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        list = findViewById(R.id.gv)
        sp = findViewById(R.id.sp)
        var city = arrayOf("Rajkot","Surat","Vadodara","Gondal")

        var adapter  = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, city)
        sp.adapter = adapter

    }

}