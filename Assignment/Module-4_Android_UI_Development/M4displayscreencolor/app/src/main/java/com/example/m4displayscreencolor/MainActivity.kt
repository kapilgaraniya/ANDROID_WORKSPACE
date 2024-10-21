package com.example.m4displayscreencolor

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 13 => Write a program in android display screen color which the Color will be
//         select from the radio button.

class MainActivity : AppCompatActivity(){

    lateinit var rg:RadioGroup
    lateinit var relativeLayout:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rg = findViewById(R.id.rg)
        relativeLayout = findViewById(R.id.main)

        rg.setOnCheckedChangeListener { RG, id ->

            when (id) {

                R.id.rb1 -> relativeLayout.setBackgroundColor(Color.RED)
                R.id.rb2 -> relativeLayout.setBackgroundColor(Color.BLUE)
                R.id.rb3 -> relativeLayout.setBackgroundColor(Color.DKGRAY)
                R.id.rb4 -> relativeLayout.setBackgroundColor(Color.MAGENTA)
                R.id.rb5 -> relativeLayout.setBackgroundColor(Color.YELLOW)

            }
        }

    }

}