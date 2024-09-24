package com.example.amul_app_task

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var t1:TextView
    lateinit var im1:ImageView

    lateinit var t2:TextView
    lateinit var im2:ImageView

    lateinit var t3:TextView
    lateinit var im3:ImageView

    lateinit var t4:TextView
    lateinit var im4:ImageView

    lateinit var t5:TextView
    lateinit var im5:ImageView

    lateinit var t6:TextView
    lateinit var im6:ImageView

    lateinit var t7:TextView
    lateinit var im7:ImageView

    lateinit var t8:TextView
    lateinit var im8:ImageView

    lateinit var t9:TextView
    lateinit var im9:ImageView

    lateinit var t10:TextView
    lateinit var im10:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        im1 = findViewById(R.id.img1)
        im2 = findViewById(R.id.img2)
        im3 = findViewById(R.id.img3)
        im4 = findViewById(R.id.img4)
        im5 = findViewById(R.id.img5)
        im6 = findViewById(R.id.img6)
        im7 = findViewById(R.id.img7)
        im8 = findViewById(R.id.img8)
        im9 = findViewById(R.id.img9)
        im10 = findViewById(R.id.img10)


        im1.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Atta", Toast.LENGTH_SHORT).show()
        }



        im2.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Chocolate", Toast.LENGTH_SHORT).show()
        }


        im3.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Ghee", Toast.LENGTH_SHORT).show()
        }


        im4.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Moshed potato", Toast.LENGTH_SHORT).show()
        }


        im5.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Butter", Toast.LENGTH_SHORT).show()
        }


        im6.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Honey", Toast.LENGTH_SHORT).show()
        }


        im7.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Lassi", Toast.LENGTH_SHORT).show()
        }


        im8.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Sport Drink", Toast.LENGTH_SHORT).show()
        }


        im9.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Kool Drink (Rose)", Toast.LENGTH_SHORT).show()
        }



        im10.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Milk", Toast.LENGTH_SHORT).show()
        }


        t1 = findViewById(R.id.atta)
        t2 = findViewById(R.id.chocolate)
        t3 = findViewById(R.id.ghee)
        t4 = findViewById(R.id.potato)
        t5 = findViewById(R.id.butter)
        t6 = findViewById(R.id.honey)
        t7 = findViewById(R.id.lassi)
        t8 = findViewById(R.id.sportd)
        t9 = findViewById(R.id.kool)
        t10 = findViewById(R.id.milk)


        t1.setOnClickListener{
            var a = Intent(applicationContext,Atta::class.java)
            startActivities(arrayOf(a))
        }


        t2.setOnClickListener{
            var b = Intent(applicationContext,chocolate::class.java)
            startActivities(arrayOf(b))
        }


        t3.setOnClickListener{
            var c = Intent(applicationContext,Ghee::class.java)
            startActivities(arrayOf(c))
        }


        t4.setOnClickListener{
            var d = Intent(applicationContext,potato::class.java)
            startActivities(arrayOf(d))
        }


        t5.setOnClickListener{
            var e = Intent(applicationContext,butter::class.java)
            startActivities(arrayOf(e))
        }


        t6.setOnClickListener{
            var f = Intent(applicationContext,honey::class.java)
            startActivities(arrayOf(f))
        }


        t7.setOnClickListener{
            var g = Intent(applicationContext,lassi::class.java)
            startActivities(arrayOf(g))
        }


        t8.setOnClickListener{
            var h = Intent(applicationContext,sport_drink::class.java)
            startActivities(arrayOf(h))
        }


        t9.setOnClickListener{
            var i = Intent(applicationContext,kool_drink::class.java)
            startActivities(arrayOf(i))
        }


        t10.setOnClickListener{
            var j = Intent(applicationContext,milk::class.java)
            startActivities(arrayOf(j))
        }
    }

}