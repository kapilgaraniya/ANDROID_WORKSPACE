package com.example.shreeramcafe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class checkbox : AppCompatActivity() {

    lateinit var chk1:CheckBox
    lateinit var chk2:CheckBox
    lateinit var chk3:CheckBox
    lateinit var chk4:CheckBox
    lateinit var btn1:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkbox)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        chk1 = findViewById(R.id.chk1)
        chk2 = findViewById(R.id.chk2)
        chk3 = findViewById(R.id.chk3)
        chk4 = findViewById(R.id.chk4)

        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener{

            var amount = 0
            var builder = StringBuilder("")

            if (chk1.isChecked)
            {
                amount += 150
                builder.append("Burger @ RS 150,\n")
            }
            if (chk2.isChecked)
            {
                amount += 50
                builder.append("Mojito @ RS 50,\n")

            }
            if (chk3.isChecked)
            {
                amount += 100
                builder.append("Pizza @ RS 100,\n")

            }
            if (chk4.isChecked)
            {
                amount += 30
                builder.append("Coffee @ RS 30,\n")

            }

            builder.append("\nTotal :- $amount")
            Log.d("Bill",builder.toString())
            Toast.makeText(applicationContext, ""+builder.toString(), Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext, bill::class.java)
            i.putExtra("kapil",builder.toString())
            startActivity(i)
        }


    }
}