package com.example.shreeramcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {

    lateinit var uname:EditText
    lateinit var pass:EditText
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        uname = findViewById(R.id.uname)
        pass = findViewById(R.id.pass)
        btn = findViewById(R.id.btn)

        btn.setOnClickListener{

            var un = uname.text.toString().trim()
            var ps = pass.text.toString()

            if (un.length==0 && ps.length==0)
            {
                uname.setError("Please Enter Username...!!")
                pass.setError("Please Enter Password...!!")
            }

            else if (un.length == 0)
            {
                uname.setError("Please Enter Username...!!")
            }

            else if (ps.length == 0)
            {
                pass.setError("Please Enter Password...!!")
            }

            else
            {
                if (un.equals("kapilahir") && ps.equals("4907"))
                {
                    Toast.makeText(applicationContext, "Welcome To Shree Ram Cafe", Toast.LENGTH_SHORT).show()

                    var i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
                else{
                    Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}