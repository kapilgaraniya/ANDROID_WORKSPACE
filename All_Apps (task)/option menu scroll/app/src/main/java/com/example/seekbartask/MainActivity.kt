package com.example.seekbartask

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{

    lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {

            R.id.call ->
            {
                var call = "8200047900"
                var cl = Intent(Intent.ACTION_DIAL)
                cl.setData(Uri.parse("tel:"+call))
                startActivity(cl)
            }

            R.id.i1 ->
            {
                var web = "https://www.android.com/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(web))
                startActivity(w)

                Toast.makeText(applicationContext, "Android", Toast.LENGTH_SHORT).show()
            }

            R.id.i2 ->
            {
                var web = "https://www.java.com/en/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(web))
                startActivity(w)

                Toast.makeText(applicationContext, "Java", Toast.LENGTH_SHORT).show()
            }

            R.id.i3 ->
            {
                var web = "https://www.php.net/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(web))
                startActivity(w)

                Toast.makeText(applicationContext, "Php", Toast.LENGTH_SHORT).show()
            }

            R.id.i4 ->
            {
                var web = "https://flutter.dev/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(web))
                startActivity(w)

                Toast.makeText(applicationContext, "Flutter", Toast.LENGTH_SHORT).show()
            }

            R.id.nextpage ->
            {
                var i = Intent(applicationContext, MainActivity2::class.java)
                startActivity(i)

                Toast.makeText(applicationContext, "Open next Page", Toast.LENGTH_SHORT).show()
            }
        }


        return super.onOptionsItemSelected(item)

        //--------------------------------------------------

    }

}