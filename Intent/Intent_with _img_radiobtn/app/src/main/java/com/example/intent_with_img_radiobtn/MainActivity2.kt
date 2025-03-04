package com.example.intent_with_img_radiobtn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity2 : AppCompatActivity() {

    private lateinit var circleImageView: CircleImageView
    private lateinit var rollNoTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize UI elements
        circleImageView = findViewById(R.id.circleImageView)
        rollNoTextView = findViewById(R.id.textViewRollNo)
        nameTextView = findViewById(R.id.textViewName)
        cityTextView = findViewById(R.id.textViewCity)
        genderTextView = findViewById(R.id.textViewGender)
        backButton = findViewById(R.id.buttonBack)

        // Get data from intent
        val rollNo = intent.getStringExtra("ROLL_NO")
        val name = intent.getStringExtra("NAME")
        val city = intent.getStringExtra("CITY")
        val gender = intent.getStringExtra("GENDER")
        val imageUriString = intent.getStringExtra("IMAGE_URI")

        // Set data to views
        rollNoTextView.text = rollNo
        nameTextView.text = name
        cityTextView.text = city
        genderTextView.text = gender

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            circleImageView.setImageURI(imageUri)
        }

        // Back button to return to the previous activity
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
