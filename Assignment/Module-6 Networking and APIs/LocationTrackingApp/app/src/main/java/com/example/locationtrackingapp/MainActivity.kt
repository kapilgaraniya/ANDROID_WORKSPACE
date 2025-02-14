package com.example.locationtrackingapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var locationTextView: TextView

    var locationReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {

            var latitude = intent?.getDoubleExtra("latitude", 0.0)
            var longitude = intent?.getDoubleExtra("longitude", 0.0)
            locationTextView.text = "Lat: $latitude, Lng: $longitude"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.locationTextView)

        requestPermissions()

        findViewById<Button>(R.id.startServiceBtn).setOnClickListener {

            if (hasRequiredPermissions()) {

                startForegroundService(Intent(applicationContext, LocationService::class.java))  // Use startForegroundService for Android 8+
            }
            else {

                Toast.makeText(applicationContext, "Permissions are required!", Toast.LENGTH_SHORT).show()
            }

        }

        findViewById<Button>(R.id.stopServiceBtn).setOnClickListener {

            Toast.makeText(applicationContext, "stop", Toast.LENGTH_SHORT).show()

            var stopIntent = Intent(applicationContext, LocationService::class.java)
            stopIntent.action = "STOP_SERVICE"
            startService(stopIntent)
        }


        var intentFilter = IntentFilter("LOCATION_UPDATE")
        var flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Context.RECEIVER_EXPORTED else 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            registerReceiver(locationReceiver, intentFilter, flags)
        }
    }

    private fun requestPermissions() {
        var permissions = mutableListOf(

            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }

        requestPermissionsLauncher.launch(permissions.toTypedArray())
    }

    var requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (!permissions.values.all { it }) {
            Toast.makeText(applicationContext, "Permissions are required for location tracking", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.plus(Manifest.permission.FOREGROUND_SERVICE)
        }
        return permissions.all { ContextCompat.checkSelfPermission(applicationContext, it) == PackageManager.PERMISSION_GRANTED }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(locationReceiver)
    }
}
