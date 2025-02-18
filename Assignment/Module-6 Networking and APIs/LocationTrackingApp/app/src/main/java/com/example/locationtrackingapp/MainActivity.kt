package com.example.locationtrackingapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity() {

    lateinit var locationTextView: TextView
    lateinit var startServiceBtn: Button
    lateinit var stopServiceBtn: Button

    private var isServiceRunning = false

    var locationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val latitude = intent?.getDoubleExtra("latitude", 0.0)
            val longitude = intent?.getDoubleExtra("longitude", 0.0)
            locationTextView.text = "Lat: $latitude, Lng: $longitude"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.locationTextView)
        startServiceBtn = findViewById(R.id.startServiceBtn)
        stopServiceBtn = findViewById(R.id.stopServiceBtn)

        requestPermissions()
        checkLocationSettings()

        startServiceBtn.setOnClickListener {
            if (hasRequiredPermissions()) {
                if (!isServiceRunning) {
                    startForegroundService(Intent(applicationContext, LocationService::class.java))
                    Toast.makeText(this, "Location Service Started", Toast.LENGTH_SHORT).show()
                    isServiceRunning = true
                }
            } else {
                requestPermissions()
            }
        }

        stopServiceBtn.setOnClickListener {
            if (isServiceRunning) {
                val stopIntent = Intent(applicationContext, LocationService::class.java)
                stopIntent.action = "STOP_SERVICE"
                startService(stopIntent)
                Toast.makeText(this, "Location Service Stopped", Toast.LENGTH_SHORT).show()
                isServiceRunning = false
            }
        }

        val intentFilter = IntentFilter("LOCATION_UPDATE")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.registerReceiver(this, locationReceiver, intentFilter, ContextCompat.RECEIVER_EXPORTED)
        } else {
            registerReceiver(locationReceiver, intentFilter)
        }
    }

    private fun requestPermissions() {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }
        requestPermissionsLauncher.launch(permissions.toTypedArray())
    }

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.values.all { it }) {
                startForegroundService(Intent(applicationContext, LocationService::class.java))
            } else {
                Toast.makeText(applicationContext, "Permissions are required for location tracking", Toast.LENGTH_SHORT).show()
            }
        }

    private fun hasRequiredPermissions(): Boolean {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }
        return permissions.all { ContextCompat.checkSelfPermission(applicationContext, it) == PackageManager.PERMISSION_GRANTED }
    }

    private fun checkLocationSettings() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(builder.build())
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(this, 1001)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        sendEx.printStackTrace()
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(locationReceiver)
    }
}
