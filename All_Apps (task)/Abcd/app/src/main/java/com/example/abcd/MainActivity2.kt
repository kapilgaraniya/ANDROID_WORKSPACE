package com.example.abcd

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var mediaPlayer= MediaPlayer.create(applicationContext,R.raw.intro)

        mediaPlayer.start()


        Toast.makeText(applicationContext, "Welcome ", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable
        {
            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)

        },3000)

    }
}