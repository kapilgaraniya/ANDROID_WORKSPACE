package com.example.m4nedittext

import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q 10 => create an application to display n edittext where n is number input by user

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var btn1:Button
    lateinit var linearLayout:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        linearLayout = findViewById(R.id.ll)

        btn1.setOnClickListener {

            var num = edt1.text.toString()

            val num2 = num.toInt()

            linearLayout.removeAllViews()

            for (i in 1..num2)
            {

                var editText = EditText(this)
                editText.layoutParams = LinearLayout.LayoutParams(

                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )

                editText.hint = "Edit Text $i"
                editText.inputType = InputType.TYPE_CLASS_TEXT

                linearLayout.addView(editText)
            }
        }

    }
}