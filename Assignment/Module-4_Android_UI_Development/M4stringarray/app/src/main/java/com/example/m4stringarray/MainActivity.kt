package com.example.m4stringarray

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 12 => Write a program to create one string array in string.xml file and that array show in listview

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.list)
        var stringArray = resources.getStringArray(R.array.my_stirng_array)

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray)
        listView.adapter = adapter
    }
}