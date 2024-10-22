package com.example.m4textviewintablelayout

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Q 7 => Create an application to add TextView in TableLayout Programmatically

class MainActivity : AppCompatActivity() {

    lateinit var TableLayout:TableLayout
    lateinit var btn1:FloatingActionButton

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

        TableLayout = findViewById(R.id.table)
        btn1 = findViewById(R.id.btn1)

        var count = 1

        btn1.setOnClickListener{

            var tablrrow = TableRow(this)
            var textView = TextView(this)

                textView.text = "Row $count, TextView"
                textView.setTextColor(Color.BLACK)
                textView.textSize = 18f

            tablrrow.addView(textView)
            TableLayout.addView(tablrrow)

            count ++
        }



    }
}