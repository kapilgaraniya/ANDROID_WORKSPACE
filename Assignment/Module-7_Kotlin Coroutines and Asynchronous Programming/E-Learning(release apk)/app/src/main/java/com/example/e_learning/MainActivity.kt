package com.example.e_learning

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var sharedPreferences: SharedPreferences
    lateinit var txt1: TextView
    lateinit var gridView: GridView
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.rgb(75,0,130)

        img1 = findViewById(R.id.Contectusimg)
        img2 = findViewById(R.id.Aboutusimg)
        sharedPreferences = getSharedPreferences("kapil", MODE_PRIVATE)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        txt1 = findViewById(R.id.txt1)
        databaseHelper = DatabaseHelper(this)

        txt1.text = "${sharedPreferences.getString("n1", "Username")}"

        gridView = findViewById(R.id.gridView)
        val options = listOf("1) Play Quiz", "2) Read Questions")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                view.setTextColor(Color.BLACK) // Set text color to black
                return view
            }
        }

        gridView.adapter = adapter


        gridView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(applicationContext, PlayQuizActivity::class.java))
                1 -> startActivity(Intent(applicationContext, ReadQuestionsActivity::class.java))
            }
        }

        img1.setOnClickListener {
            Toast.makeText(applicationContext, "Contact us", Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this)
                .setTitle("Contact Us")
                .setMessage("We value your feedback and are here to assist you with any questions or issues you may have. Feel free to reach out to us through the following channels:\n\n" +
                        "\uD83D\uDCE7 Email: support@elearningapp.com\n\n" +
                        "\uD83D\uDCF1 Phone: +91-123-456-7890\n\n" +
                        "\uD83C\uDF10 Website: www.elearningapp.com\n\n" +
                        "You can also follow us on social media for updates and new features:\n" +
                        "\uD83D\uDCD8 Facebook: facebook.com/elearningapp\n\n" +
                        "\uD83D\uDC26 Twitter: twitter.com/elearningapp\n\n" +
                        "\uD83D\uDCF8 Instagram: instagram.com/elearningapp")
                .setPositiveButton("OK", null)
                .show()
        }

        img2.setOnClickListener {
            Toast.makeText(applicationContext, "About us", Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this)
                .setTitle("About Us")
                .setMessage("Welcome to the E-Learning App!\n\n" +
                        "This app is designed to provide a seamless and engaging learning experience for users. Whether you're brushing up on your fundamentals, exploring SQL concepts, or preparing for HR interviews, this app has something for everyone.\n\n" +
                        "Key Features:\n" +
                        "- Play Quiz: Test your knowledge with an interactive offline quiz.\n" +
                        "- Read Questions: Browse through categorized questions.\n" +
                        "- Offline Mode: Learn anytime, anywhere.\n" +
                        "- User-Friendly Interface: Intuitive design for a great user experience.")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.i3 -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Logout") { _, _ ->
                        sharedPreferences.edit().clear().apply()
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "Welcome back to the app!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "App is ready to use.", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "App paused. See you soon!", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "App stopped. You can reopen it anytime.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Thanks for using the app. Goodbye!", Toast.LENGTH_SHORT).show()
    }
}
