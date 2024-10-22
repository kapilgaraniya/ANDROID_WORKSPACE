package com.example.m4numbers

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var txt1:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt1 = findViewById(R.id.txt1)
        var i = intent
        txt1.setText(i.getStringExtra("k"))

    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        super.onBackPressed()

        var alert = AlertDialog.Builder(this)
        alert.setTitle("Are you sure you want to exit?")
        alert.setPositiveButton("YES", { yes: DialogInterface, i:Int -> finishAffinity()})
        alert.setNegativeButton("NO", { no: DialogInterface, i:Int -> no.cancel()})
        alert.show()
    }
}