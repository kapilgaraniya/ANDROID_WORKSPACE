package com.example.shreeramcafe

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     lateinit var burgerTextView: TextView
     lateinit var mojitoTextView: TextView
     lateinit var coffeeTextView: TextView
     lateinit var pizzaTextView: TextView

     lateinit var burgerImageView: ImageView
     lateinit var mojitoImageView: ImageView
     lateinit var pizzaImageView: ImageView
     lateinit var coffeeImageView: ImageView

     lateinit var webTextView: TextView
     lateinit var callTextView: TextView

    private lateinit var checkboxButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        burgerTextView = findViewById(R.id.burger)
        mojitoTextView = findViewById(R.id.mojito)
        pizzaTextView = findViewById(R.id.pizza)
        coffeeTextView = findViewById(R.id.coffe)

        webTextView = findViewById(R.id.web)
        callTextView = findViewById(R.id.cnum)

        checkboxButton = findViewById(R.id.btn01)

        checkboxButton.setOnClickListener {
            val intent = Intent(this, checkbox::class.java)
            startActivity(intent)
        }

        burgerTextView.setOnClickListener {
            Toast.makeText(this, "Price : ₹299", Toast.LENGTH_SHORT).show()
        }

        mojitoTextView.setOnClickListener {
            Toast.makeText(this, "Price : ₹120", Toast.LENGTH_SHORT).show()
        }

        pizzaTextView.setOnClickListener {
            Toast.makeText(this, "Price : ₹199", Toast.LENGTH_SHORT).show()
        }

        coffeeTextView.setOnClickListener {
            Toast.makeText(this, "Price : ₹49", Toast.LENGTH_SHORT).show()
        }

        webTextView.setOnClickListener {
            val url = "https://www.justdial.com/Rajkot/Shree-Ram-Restaurant-Opposite-Shree-Raj-Vatika-Society-University-Campus/0281PX281-X281-180828135717-X4T1_BZDET"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        callTextView.setOnClickListener {
            val num = "8200047900"
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$num"))
            startActivity(intent)
        }

        burgerImageView = findViewById(R.id.burgerimg)
        mojitoImageView = findViewById(R.id.mojitoimg)
        pizzaImageView = findViewById(R.id.pizzaimg)
        coffeeImageView = findViewById(R.id.coffeeimg)

        burgerImageView.setOnClickListener {
            val intent = Intent(applicationContext, burger::class.java)
            startActivity(intent)
        }

        mojitoImageView.setOnClickListener {
            val intent = Intent(applicationContext, mojeto::class.java)
            startActivity(intent)
        }

        pizzaImageView.setOnClickListener {
            val intent = Intent(applicationContext, pizza::class.java)
            startActivity(intent)
        }

        coffeeImageView.setOnClickListener {
            val intent = Intent(applicationContext, coffee::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Are you sure you want to exit?")
        alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int -> finishAffinity() }
        alert.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel() }
        alert.show()
    }
}
