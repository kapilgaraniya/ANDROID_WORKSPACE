package com.example.listandsearchex

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeekbarActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    lateinit var img:ImageView
    lateinit var seek1:SeekBar
    lateinit var seek2:SeekBar
    lateinit var seek3:SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seekbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        img = findViewById(R.id.img)

        seek1 = findViewById(R.id.seek1)
        seek2 = findViewById(R.id.seek2)
        seek3 = findViewById(R.id.seek3)

        seek1.setOnSeekBarChangeListener(this)
        seek2.setOnSeekBarChangeListener(this)
        seek3.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean)
    {
        var r=0
        var g=0
        var b=0

        r = seek1.progress
        g = seek2.progress
        b = seek3.progress

        img.setBackgroundColor(Color.rgb(r,g,b))




    }

    override fun onStartTrackingTouch(p0: SeekBar?)
    {

    }

    override fun onStopTrackingTouch(p0: SeekBar?)
    {

    }
}