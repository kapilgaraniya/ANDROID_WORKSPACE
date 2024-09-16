package com.example.module3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var btn1:Button
lateinit var link:TextView

lateinit var uname:TextView
lateinit var pass:TextView

class Que5_login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_que5_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1 = findViewById(R.id.loginbtn)
        link = findViewById(R.id.link)
        uname = findViewById(R.id.username)
        pass = findViewById(R.id.password)

        btn1.setOnClickListener{

            var unm = uname.text.toString()
            var ps = pass.text.toString()

            if (unm.length==0 && ps.length == 0)
            {
                uname.setText("Please Enter valid Username!!")
                pass.setError("Please Enter Valid password!!")
            }

            else if(unm.length == 0 )
            {
                uname.setText("Please Enter valid Username!!")

            }

            else if(ps.length == 0 )
            {
                pass.setError("Please Enter Valid password!!")
            }

            else
            {
                if (unm.equals("kapilahir") && ps.equals("4907"))
                {
                    Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()

                    var i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        link.setOnClickListener {

            var i = Intent(applicationContext, Que5_registration::class.java)
            startActivity(i)
        }
    }
}