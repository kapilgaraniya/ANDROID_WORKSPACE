package com.example.firebase_otp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var edtphone:EditText
    lateinit var edtotp:EditText
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var auth:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtphone = findViewById(R.id.edt1)
        edtotp = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        btn1.setOnClickListener {

            var mob = edtphone.text.toString()
            sendverificationcode(mob)
        }

        btn2.setOnClickListener {

            var otp: String = edtotp.text.toString()
            verifycode(otp)
        }

    }

    private fun sendverificationcode(mob: String)
    {
        PhoneAuthProvider.getInstance().verifyphonenumber(mob, 60, TimeUnit.SECONDS, this, mcallback)
    }

    private fun verifycode(otp: String)
    {
        
    }
}