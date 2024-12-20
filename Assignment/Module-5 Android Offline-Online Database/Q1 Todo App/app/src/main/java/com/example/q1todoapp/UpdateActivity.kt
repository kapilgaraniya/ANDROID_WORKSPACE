package com.example.q1todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateActivity : AppCompatActivity() {

    lateinit var edt1: EditText
    lateinit var edt2: EditText

    lateinit var btn1: Button
    lateinit var dbHelper: Dbhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edtname)
        edt2 = findViewById(R.id.edtnum)

        btn1 = findViewById(R.id.btn1)
        dbHelper = Dbhelper(applicationContext)

        var i = intent
        var id = i.getIntExtra("id",0)

        edt1.setText(i.getStringExtra("name"))
        edt2.setText(i.getStringExtra("num"))

        btn1.setOnClickListener {

            var name = edt1.text.toString()
            var num = edt2.text.toString()

            var m = Model()
            m.id=id
            m.Tname = name
            m.Tdisc = num
            dbHelper.updatedata(m)

            var f1 = DisignFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.fl,f1).commit()

        }

    }

}