package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TableLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    lateinit var listView: ListView
    lateinit var list: MutableList<Modal>

    lateinit var toolbar: Toolbar
    lateinit var tableLayout: TableLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.list)
        list = ArrayList()

        list.add(Modal(R.drawable.u1,"Adam","1:49 pm","okay","9463521654","France"))
        list.add(Modal(R.drawable.u3,"steffie","7:55 pm","done","652254563","France"))
        list.add(Modal(R.drawable.u2,"hayato","10:25 am","byy","497861665","France"))
        list.add(Modal(R.drawable.u4,"Alok","11:49 pm","Good","6845226265","France"))
        list.add(Modal(R.drawable.u5,"kelly","9:26 am","Hou are you","155636554","France"))

        var adaptar = Myadaptor(applicationContext,list)
        listView.adapter = adaptar

        listView.setOnItemClickListener { parent, view, position, id ->

            var i = Intent(applicationContext,MainActivity2::class.java)
            i.putExtra("pos",position)
            i.putExtra("i",list.get(position).img)
            i.putExtra("n",list.get(position).txt1)
            i.putExtra("p",list.get(position).txt4)
            i.putExtra("c",list.get(position).txt5)
            startActivity(i)

        }

    }

}