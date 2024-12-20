package com.example.m5_notes_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.m5_notes_app.databinding.ActyvityAddnoteBinding

class AddNoteActivity : AppCompatActivity()
{
    private lateinit var binding: ActyvityAddnoteBinding

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var db:DatabaseClass

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActyvityAddnoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addnote)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btnadd)
        db = Room.databaseBuilder(applicationContext, DatabaseClass::class.java, "NoteAppDatabase")
            .allowMainThreadQueries()
            .build()

        if (GlobalVariables.updateflag.equals("update"))
        {
            edt1.setText(GlobalVariables.title)
            edt2.setText(GlobalVariables.content)
        }


        btn1.setOnClickListener {

            if (GlobalVariables.updateflag == "update")
            {
                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var u = User()
                u.id = GlobalVariables.id
                u.title=name
                u.content=email

                db.daoclass().updatedata(u)
                Toast.makeText(applicationContext,"Notes Updated..!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }

            else
            {
                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var u = User()
                u.title=name
                u.content=email

                db.daoclass().insertdata(u)
                Toast.makeText(applicationContext,"Note Inserted..!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        GlobalVariables.updateflag = ""
    }

}