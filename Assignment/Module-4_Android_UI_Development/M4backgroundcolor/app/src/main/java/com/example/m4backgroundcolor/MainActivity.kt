package com.example.m4backgroundcolor

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 14 => Write a program you have taken three seek bar controls. From three Seekbar which Seek bar value
//         changed the background color of device will be changed.

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var sb1:SeekBar
    lateinit var sb2:SeekBar
    lateinit var sb3:SeekBar
    lateinit var linearLayout: LinearLayout

    var red = 0
    var green = 0
    var blue = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sb1 = findViewById(R.id.sb1)
        sb2 = findViewById(R.id.sb2)
        sb3 = findViewById(R.id.sb3)
        linearLayout  = findViewById(R.id.main)


        sb1.setOnSeekBarChangeListener(this)
        sb2.setOnSeekBarChangeListener(this)
        sb3.setOnSeekBarChangeListener(this)

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        red = sb1.progress
        green = sb2.progress
        blue = sb3.progress

        linearLayout.setBackgroundColor(Color.rgb(red,blue,green))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}