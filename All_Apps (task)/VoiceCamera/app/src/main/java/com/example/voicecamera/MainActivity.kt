package com.example.voicecamera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var edt1:EditText
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var tts:TextToSpeech
    lateinit var img:ImageView

    var data = ""

    lateinit var txtv: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        img = findViewById(R.id.img)
        tts = TextToSpeech(applicationContext,this)


        btn1.setOnClickListener {

            data = edt1.text.toString()
            tts.speak(data, TextToSpeech.QUEUE_ADD,null)
        }

        btn2.setOnClickListener {

            var i = Intent(ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,1)
        }

        //------------------------------------------------

        txtv = findViewById(R.id.txt)
        button = findViewById(R.id.button_speak)

        button.setOnClickListener {

            speak()
        }

    }

    fun speak() {

        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE)

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speack Now")
        startActivityForResult(intent,99)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == 1 )
        {
            var bitmap: Bitmap? = data!!.extras!!.get("data") as Bitmap?
            img.setImageBitmap(bitmap)
        }

        if (requestCode == 99 && resultCode == RESULT_OK)
        {
            txtv?.setText(data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!![0])
        }

        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onInit(status: Int)
    {
        tts.setPitch(1.7F)
        tts.setSpeechRate(0.8F)
    }
}