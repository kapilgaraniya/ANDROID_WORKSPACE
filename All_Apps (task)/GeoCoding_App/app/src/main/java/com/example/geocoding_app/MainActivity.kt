package com.example.geocoding_app

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mapView: MapView
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Geocoding (Address to Coordinates)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val geocodeButton = findViewById<Button>(R.id.geocodeButton)
        val coordinatesOutput = findViewById<TextView>(R.id.coordinatesOutput)

        geocodeButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                getCoordinates(address, coordinatesOutput)
            } else {
                coordinatesOutput.text = "Please enter a valid address"
            }
        }

        // Reverse Geocoding (Coordinates to Address)
        val latitudeInput = findViewById<EditText>(R.id.latitudeInput)
        val longitudeInput = findViewById<EditText>(R.id.longitudeInput)
        val reverseGeocodeButton = findViewById<Button>(R.id.reverseGeocodeButton)
        val addressOutput = findViewById<TextView>(R.id.addressOutput)

        reverseGeocodeButton.setOnClickListener {
            val latitudeText = latitudeInput.text.toString()
            val longitudeText = longitudeInput.text.toString()

            if (latitudeText.isNotEmpty() && longitudeText.isNotEmpty()) {
                val latitude = latitudeText.toDoubleOrNull()
                val longitude = longitudeText.toDoubleOrNull()

                if (latitude != null && longitude != null) {
                    getAddress(latitude, longitude, addressOutput)
                } else {
                    addressOutput.text = "Please enter valid latitude and longitude values"
                }
            } else {
                addressOutput.text = "Please fill in both latitude and longitude"
            }
        }

    }

    private fun getCoordinates(address: String, output: TextView) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses!!.isNotEmpty()) {
                val location = addresses[0]
                val latitude = location.latitude
                val longitude = location.longitude
                output.text = "Latitude: $latitude\nLongitude: $longitude"

                // Update Map
                showLocationOnMap(latitude, longitude)
            } else {
                output.text = "No location found for the entered address"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            output.text = "Error: ${e.message}"
        }
    }

    private fun getAddress(latitude: Double, longitude: Double, output: TextView) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses!!.isNotEmpty()) {
                val address = addresses[0].getAddressLine(0)
                output.text = "Address: $address"

                // Update Map
                showLocationOnMap(latitude, longitude)
            } else {
                output.text = "No address found for the entered coordinates"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            output.text = "Error: ${e.message}"
        }
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Default location (e.g., New York City)
        val defaultLocation = LatLng(40.7128, -74.0060)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        // Set map click listener
        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(latLng).title("Selected Location"))
        }
    }


    private fun showLocationOnMap(lat: Double, lng: Double) {
        val location = LatLng(lat, lng)
        googleMap.clear()
        googleMap.addMarker(MarkerOptions().position(location).title("Location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    // Handle MapView lifecycle
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
