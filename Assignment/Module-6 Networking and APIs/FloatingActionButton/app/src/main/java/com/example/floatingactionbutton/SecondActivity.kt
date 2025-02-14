package com.example.floatingactionbutton

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<FloatingActionButton>(R.id.fab_back).setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
