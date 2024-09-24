package com.example.shreeramcafe

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class bill : AppCompatActivity() {

    lateinit var txt1:TextView
    lateinit var btn2:Buttonx

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bill)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt1 = findViewById(R.id.txt1)
        btn2 = findViewById(R.id.btnbill)

        var i = intent

        txt1.setText(i.getStringExtra("kapil") )



        var btndone:Button = findViewById(R.id.btnbill)

        btndone.setOnClickListener {



            val dialogView = layoutInflater.inflate(R.layout.disign, null)

            // Create the AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)

            // Create and show the dialog
            val alertDialog = builder.create()

            // Make the dialog's background transparent
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // Show the dialog
            alertDialog.show()

            // Handle button click from the custom layout

        }



//            var alert = AlertDialog.Builder(this)
//            var layout = LayoutInflater.from(applicationContext)
//            var View = layout.inflate(R.layout.disign,null)
//
//            val alertDialog = alert.create()
//            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            //alert.show()
//            // AlertDialog.Builder(this,R.style.CustomDialog)
//            //alert.window.setBackgroundDrawableResource(android.R.color.transparent);
//            //val alertDialog = alert.create()
////            alertDialog.setOnShowListener{
////                alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
////            }
//            //alert.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
//
//            alert.setView(View)
//            alert.show()
        }


    }
//}