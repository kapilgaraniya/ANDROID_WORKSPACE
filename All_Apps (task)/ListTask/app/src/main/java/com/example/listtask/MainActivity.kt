package com.example.listtask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var listtview: ListView
    lateinit var list: MutableList<String>
    lateinit var edittext: EditText
    lateinit var btn: Button

    lateinit var searchView: SearchView
    lateinit var btnSpeak: Button
    lateinit var btnSearchSpeak: Button  // New button for search speech-to-text

    lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = findViewById(R.id.srch)
        listtview = findViewById(R.id.list)
        list = ArrayList()

        edittext = findViewById(R.id.edt1)
        btn = findViewById(R.id.btn1)
        btnSpeak = findViewById(R.id.button_speak)
        btnSearchSpeak = findViewById(R.id.button_search_speak)  // Initialize the search speak button

        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listtview.adapter = adapter

        // Add text manually
        btn.setOnClickListener {
            val data = edittext.text.toString()
            if (data.isNotEmpty()) {
                list.add(data)
                adapter.notifyDataSetChanged()
                edittext.text.clear()
                Toast.makeText(applicationContext, "Data Added Successfully...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Please enter some text", Toast.LENGTH_SHORT).show()
            }
        }

        // Speech-to-text functionality for adding items
        btnSpeak.setOnClickListener {
            speakForList()
        }

        // Speech-to-text functionality for search
        btnSearchSpeak.setOnClickListener {
            speakForSearch()
        }

        // Search filter functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    // Speech-to-text for adding items to the list
    private fun speakForList() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "SPEAK NOW")

        try {
            startActivityForResult(intent, 99)
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Your device does not support speech input", Toast.LENGTH_SHORT).show()
        }
    }

    // Speech-to-text for search input
    private fun speakForSearch() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "SPEAK TO SEARCH")

        try {
            startActivityForResult(intent, 100)  // Use a different request code for search speech
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Your device does not support speech input", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 99 && resultCode == RESULT_OK && data != null) {
            // Handle speech result for adding items to the list
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                val spokenText = result[0]
                list.add(spokenText)
                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Spoken text added: $spokenText", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            // Handle speech result for search input
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                val spokenQuery = result[0]
                searchView.setQuery(spokenQuery, false)  // Set spoken text to the SearchView
                adapter.filter.filter(spokenQuery)       // Filter the list based on spoken query
                Toast.makeText(applicationContext, "Searching for: $spokenQuery", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
