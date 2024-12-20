package com.example.m5_notes_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.m5_notes_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db: DatabaseClass
    lateinit var list: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        db = Room.databaseBuilder(applicationContext, DatabaseClass::class.java, "NoteAppDatabase").allowMainThreadQueries().build()
        list = ArrayList()

        var rm: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = rm

        list = db.daoclass().viewdata()

        var adapter = MyAdapter(applicationContext,list)
        binding.recycler.adapter = adapter

        binding.f1.setOnClickListener {

            startActivity(Intent(applicationContext,AddNoteActivity::class.java))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}