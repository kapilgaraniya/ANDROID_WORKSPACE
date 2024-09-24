package com.example.listandsearchex

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var listview:ListView
    lateinit var searchView: SearchView
    lateinit var list:MutableList<String>


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

        listview = findViewById(R.id.list)
        searchView=findViewById(R.id.search)
        list = ArrayList()

        list.add("android")
        list.add("java")
        list.add("php")
        list.add("ios")
        list.add("angular")

        var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
        listview.adapter=adapter

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean
            {
//                var finalvalue = p0!!.trim()
//
//                if(list.contains(finalvalue))
//                {
//                    adapter.filter.filter(p0)
//                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean
            {
                adapter.filter.filter(p0)

                return false
            }
        })


    }


}