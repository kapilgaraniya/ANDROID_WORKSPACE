package com.example.seekbartask

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity(), RatingBar.OnRatingBarChangeListener {

    lateinit var ratingBar: RatingBar
    lateinit var btnsubmit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ratingBar = findViewById(R.id.rb)
        btnsubmit = findViewById(R.id.btnsubmit)

        ratingBar.setOnRatingBarChangeListener(this)


    }

    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromuser: Boolean)
    {
        Toast.makeText(applicationContext, ""+ratingBar!!.rating, Toast.LENGTH_SHORT).show()
    }
}