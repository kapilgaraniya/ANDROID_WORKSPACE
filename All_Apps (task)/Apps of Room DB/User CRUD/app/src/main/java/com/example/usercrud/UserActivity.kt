package com.example.usercrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.usercrud.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityUserBinding

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var db:DatabaseClass

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)
        db = Room.databaseBuilder(applicationContext, DatabaseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()

        if (GlobalVariables.updateflag.equals("update"))
        {
            edt1.setText(GlobalVariables.name)
            edt2.setText(GlobalVariables.email)
        }


        btn1.setOnClickListener {

            if (GlobalVariables.updateflag == "update")
            {
                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var u = User()
                u.id = GlobalVariables.id
                u.name=name
                u.email=email

                db.daoclass().updatedata(u)
                Toast.makeText(applicationContext,"User Updated..!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }

            else
            {
                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var u = User()
                u.name=name
                u.email=email

                db.daoclass().insertdata(u)
                Toast.makeText(applicationContext,"User Inserted..!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }

        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        GlobalVariables.updateflag = ""
    }

}