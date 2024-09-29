package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity()
{

    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

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

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewpager)
        setviewpager()

        tabLayout = findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

    }

    private fun setviewpager()
    {
        var adapter = MyAdapter(applicationContext, supportFragmentManager)
        adapter.tops("Chat",ChatFragment())
        adapter.tops("Status",StatusFragment())
        adapter.tops("Call",CallFragment())
        viewPager.adapter = adapter

    }
}