package com.example.m4toolbarq15

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.widget.SearchView

// Q 15 =>  Create custom toolbar as per given below design
//          Create toolbar with spinner and search functionality 

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var searchView: SearchView
    lateinit var spinner: Spinner
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        searchView = findViewById(R.id.searchView)
        spinner = findViewById(R.id.spinner)
        listView = findViewById(R.id.listView)

        setSupportActionBar(toolbar)

        var sItems = listOf("Kotlin", "Java", "Php", "Flutter", "Python")

        var sAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sItems)
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = sAdapter

        var list = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")

        var lAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = lAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                lAdapter.filter.filter(newText)

                return false
            }
        })

    }
}