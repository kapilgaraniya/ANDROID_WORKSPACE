package com.example.q1todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn:Button
    lateinit var dbhelper: Dbhelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn = findViewById(R.id.btnTask)
        dbhelper = Dbhelper(applicationContext)

        btn.setOnClickListener {

            var task = edt1.text.toString()
            var disc = edt2.text.toString()

            var m = Model()
            m.Tname = task
            m.Tdisc = disc

            var id = dbhelper.insertdata(m)
            Toast.makeText(applicationContext, "Task Inserted => "+id, Toast.LENGTH_SHORT).show()

            var f1 = DisignFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.fl,f1).commit()
        }

    }
}
