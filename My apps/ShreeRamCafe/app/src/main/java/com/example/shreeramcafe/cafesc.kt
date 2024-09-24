package com.example.shreeramcafe

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class cafesc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cafesc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler().postDelayed(Runnable
        {
            var i  = Intent(applicationContext, login::class.java)
            startActivity(i)
        },3000)
    }
    override fun onBackPressed(){
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Are you sure you want to exit ?")
        alert.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish()})
        alert.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel()})
        alert.show()
    }
}