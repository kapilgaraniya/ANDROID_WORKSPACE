package com.example.calculatorapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var btnClearHistory: Button
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.listViewHistory)
        btnClearHistory = findViewById(R.id.btnClearHistory)
        dbHelper = DBHelper(this)

        loadHistory()

        btnClearHistory.setOnClickListener {
            dbHelper.deleteHistory()
            loadHistory()
            Toast.makeText(this, "History Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadHistory() {
        val historyList = dbHelper.getHistory()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList)
        listView.adapter = adapter
    }
}
