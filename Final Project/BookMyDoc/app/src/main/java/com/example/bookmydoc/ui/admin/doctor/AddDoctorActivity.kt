package com.example.bookmydoc.ui.admin.doctor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bookmydoc.data.Doctor
import com.example.bookmydoc.R
import com.example.bookmydoc.ui.admin.doctor.ManageDoctorsActivity
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AddDoctorActivity : AppCompatActivity() {

    private lateinit var etDoctorName: EditText
    private lateinit var etDoctorSpeciality: EditText
    private lateinit var etDoctorExperience: EditText
    private lateinit var etDoctorEmail: EditText
    private lateinit var btnUploadImage: Button
    private lateinit var ivDoctorImage: ImageView
    private lateinit var btnSaveDoctor: Button
    private lateinit var progressBar: ProgressBar

    private var selectedImageUri: Uri? = null
    private var selectedImageBase64: String = "" // Store Base64 string
    private val firebaseDatabase = FirebaseDatabase.getInstance().getReference("Doctors")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)

        etDoctorName = findViewById(R.id.etDoctorName)
        etDoctorSpeciality = findViewById(R.id.etDoctorSpeciality)
        etDoctorExperience = findViewById(R.id.etDoctorExperience)
        etDoctorEmail = findViewById(R.id.etDoctorEmail)
        btnUploadImage = findViewById(R.id.btnUploadImage)
        ivDoctorImage = findViewById(R.id.ivDoctorImage)
        btnSaveDoctor = findViewById(R.id.btnSaveDoctor)
        progressBar = findViewById(R.id.progressBar)

        btnUploadImage.setOnClickListener {
            openGallery()
        }

        btnSaveDoctor.setOnClickListener {
            saveDoctor()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data?.data != null) {
            selectedImageUri = data.data

            // Convert the image to Base64 string
            selectedImageBase64 = convertImageToBase64(selectedImageUri!!)

            // Show image preview
            Picasso.get().load(selectedImageUri).into(ivDoctorImage)
        }
    }

    // Convert Image URI to Base64 String
    private fun convertImageToBase64(imageUri: Uri): String {
        val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream) // Compress image
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun saveDoctor() {
        val name = etDoctorName.text.toString().trim()
        val speciality = etDoctorSpeciality.text.toString().trim()
        val experience = etDoctorExperience.text.toString().trim()
        val email = etDoctorEmail.text.toString().trim()

        if (name.isEmpty() || speciality.isEmpty() || experience.isEmpty() || email.isEmpty() || selectedImageBase64.isEmpty()) {
            Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE

        val doctorId = firebaseDatabase.push().key ?: return
        val doctor = Doctor(doctorId, name, speciality, experience, email, selectedImageBase64)

        firebaseDatabase.child(doctorId).setValue(doctor)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Doctor added successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, ManageDoctorsActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Failed to add doctor", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                progressBar.visibility = View.GONE
            }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
