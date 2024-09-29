package com.example.milkonmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity(), PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    lateinit var btn:Button
    lateinit var btnpluse:Button
    lateinit var btnminus:Button
    lateinit var progressBar: ProgressBar
    lateinit var webView: WebView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn = findViewById(R.id.btn2_1)

        btn.setOnClickListener {

            var popupmenu = PopupMenu(applicationContext,btn)
            popupmenu.menuInflater.inflate(R.menu.popupmenu,popupmenu.menu)
            popupmenu.setOnMenuItemClickListener(this)
            popupmenu.show()
        }
        //-----------------------------------------------

        progressBar = findViewById(R.id.pb)

        btnpluse = findViewById(R.id.btnplus)
        btnminus = findViewById(R.id.btnminus)

        btnpluse.setOnClickListener(this)
        btnminus.setOnClickListener(this)


        webView = findViewById(R.id.wv)
        webView.loadUrl("https://careercenter.tops-int.com/")

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean
    {

        when(item!!.itemId)
        {
            R.id.i6->
            {
                Toast.makeText(applicationContext, "popup menu clicked", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
    //--------------------------------------------------

    override fun onClick(v: View?) {

        if (v == btnpluse)
        {
            progressBar.incrementProgressBy(1)
            setProgress(100*progressBar.progress)
        }

        if (v == btnminus)
        {
            progressBar.incrementProgressBy(-1)
            setProgress(100*progressBar.progress)
        }

    }

}