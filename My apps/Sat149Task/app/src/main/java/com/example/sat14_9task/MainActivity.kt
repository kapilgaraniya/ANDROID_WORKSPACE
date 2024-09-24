package com.example.sat14_9task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt01)
        edt2 = findViewById(R.id.edt02)
        btn = findViewById(R.id.btn01)

        btn.setOnClickListener{

            var fn1 = edt1.text.toString()
            var sn2 = edt2.text.toString()

            var fn = fn1.toInt()
            var sn = sn2.toInt()

            fn += 1
            sn -= 1

            var builder = StringBuilder("")

            for (x in fn..sn)
            {
                Log.d("thisis",x.toString())
                builder.append("\n-> ",x)
                var i = Intent(applicationContext, MainActivity2::class.java)
                i.putExtra("k",builder.toString())
                startActivity(i)
            }
        }
    }
}
