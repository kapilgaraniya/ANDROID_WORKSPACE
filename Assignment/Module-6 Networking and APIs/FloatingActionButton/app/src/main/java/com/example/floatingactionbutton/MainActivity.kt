package com.example.floatingactionbutton

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var fabnext: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        fabnext = findViewById(R.id.fab_next)

        fab.setOnClickListener { view ->

            Snackbar.make(view, "Floating Action Button Clicked", Snackbar.LENGTH_LONG).show()
        }

        fabnext.setOnClickListener {

            startActivity(Intent(applicationContext, SecondActivity::class.java))

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

    }
}