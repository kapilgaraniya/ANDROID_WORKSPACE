package com.example.listtask

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Objects

class MainActivity : AppCompatActivity(){

    lateinit var listtview:ListView
    lateinit var list:MutableList<String>
    lateinit var edittext:EditText
    lateinit var btn:Button

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = findViewById(R.id.srch)

        listtview = findViewById(R.id.list)
        list = ArrayList()

        edittext = findViewById(R.id.edt1)
        btn = findViewById(R.id.btn1)

        var adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,list)
        listtview.adapter = adapter

            btn.setOnClickListener {
                Toast.makeText(applicationContext, "Data Added Successfully...", Toast.LENGTH_SHORT)
                    .show()

                var data = edittext.text.toString()
                list.add(data)
                adapter.notifyDataSetChanged()
            }

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                adapter.filter.filter(p0)

                return false
            }


        })
    }

}