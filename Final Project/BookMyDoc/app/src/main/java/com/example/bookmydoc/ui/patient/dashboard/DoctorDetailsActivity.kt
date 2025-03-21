package com.example.bookmydoc.ui.patient.dashboard

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.Window
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmydoc.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayInputStream

class DoctorDetailsActivity : AppCompatActivity() {

    private lateinit var txtDoctorName: TextView
    private lateinit var txtSpeciality: TextView
    private lateinit var txtExperience: TextView
    private lateinit var txtDoctorRating: TextView
    private lateinit var imgDoctor: CircleImageView
    private lateinit var btnRateDoctor: Button
    private var doctorId: String? = null
    private lateinit var databaseReference: DatabaseReference
    private val userId: String by lazy { FirebaseAuth.getInstance().currentUser?.uid ?: "unknown_user" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        txtDoctorName = findViewById(R.id.txtDoctorName)
        txtSpeciality = findViewById(R.id.txtSpeciality)
        txtExperience = findViewById(R.id.txtExperience)
        txtDoctorRating = findViewById(R.id.txtDoctorRating)
        imgDoctor = findViewById(R.id.imgDoctor)
        btnRateDoctor = findViewById(R.id.btnRateDoctor)

        doctorId = intent.getStringExtra("doctorId")
        val doctorName = intent.getStringExtra("doctorName") ?: "N/A"
        val doctorSpeciality = intent.getStringExtra("doctorSpeciality") ?: "N/A"
        val doctorExperience = intent.getStringExtra("doctorExperience") ?: "N/A"
        val doctorImage = intent.getStringExtra("doctorImage") ?: ""
        val doctorRating = intent.getFloatExtra("doctorAverageRating", 0.0f)

        txtDoctorName.text = doctorName
        txtSpeciality.text = doctorSpeciality
        txtExperience.text = doctorExperience
        txtDoctorRating.text = getStarRatingText(doctorRating)

        loadDoctorImage(doctorImage)

        databaseReference = FirebaseDatabase.getInstance().getReference("DoctorRatings")

        // Check if the user has already rated this doctor
        isDoctorRated(doctorId!!)

        btnRateDoctor.setOnClickListener {
            showRatingDialog()
        }
    }

    private fun getStarRatingText(rating: Float): String {
        val stars = "\u2B50".repeat(rating.toInt()) // Unicode for ⭐
        return String.format("%.1f %s", rating, stars)
    }

    private fun loadDoctorImage(base64String: String?) {
        if (!base64String.isNullOrEmpty()) {
            try {
                val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(decodedBytes))
                imgDoctor.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Toast.makeText(this, "Error decoding image", Toast.LENGTH_SHORT).show()
                imgDoctor.setImageResource(R.drawable.ic_profile)
            }
        } else {
            imgDoctor.setImageResource(R.drawable.ic_patient)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun showRatingDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_rate_doctor)
        dialog.setCancelable(true)

        val ratingBarDialog = dialog.findViewById<RatingBar>(R.id.dialogRatingBar)
        val btnSubmitRating = dialog.findViewById<Button>(R.id.btnSubmitRating)

        btnSubmitRating.setOnClickListener {
            val rating = ratingBarDialog.rating
            if (rating == 0.0f) {
                Toast.makeText(this, "Please select a rating", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveDoctorRating(doctorId!!, rating)
            Toast.makeText(this, "You rated: $rating stars", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun saveDoctorRating(doctorId: String, rating: Float) {
        val ratingRef = databaseReference.child(doctorId).child(userId)
        ratingRef.setValue(rating).addOnSuccessListener {
            disableRatingButton()
            updateDoctorRating(doctorId)
        }
    }

    private fun isDoctorRated(doctorId: String) {
        val ratingRef = databaseReference.child(doctorId).child(userId)

        ratingRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                disableRatingButton()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun disableRatingButton() {
        btnRateDoctor.isEnabled = false
        btnRateDoctor.text = "Already Rated"
        btnRateDoctor.setBackgroundResource(R.drawable.green_button_bg)
    }

    private fun updateDoctorRating(doctorId: String) {
        val ratingRef = databaseReference.child(doctorId)

        ratingRef.get().addOnSuccessListener { snapshot ->
            var totalRating = 0f
            var count = 0

            for (child in snapshot.children) {
                val rating = child.getValue(Float::class.java)
                if (rating != null) {
                    totalRating += rating
                    count++
                }
            }

            if (count > 0) {
                val avgRating = totalRating / count
                txtDoctorRating.text = getStarRatingText(avgRating)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch ratings", Toast.LENGTH_SHORT).show()
        }
    }
}
