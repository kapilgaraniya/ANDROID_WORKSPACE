package com.example.googleurl

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Q : 5 =>  Create an application to load google url into webview also manage forward and backward redirect
// In Previous Practical write code to check weather internet connection is available or not if
// no internet connection then display message accordingly

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var btnBack: Button
    lateinit var btnForward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        btnBack = findViewById(R.id.btnBack)
        btnForward = findViewById(R.id.btnForward)

        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")

        if (isInternetAvailable())
        {
            webView.loadUrl("https://www.google.com")
        }

        else
        {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
        }
        

        btnBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }

        btnForward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }
    }

    private fun isInternetAvailable(): Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }

}