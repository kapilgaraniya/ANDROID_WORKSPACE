package com.example.bookmydoc.ui.admin.doctor

import android.annotation.SuppressLint
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
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class EditDoctorActivity : AppCompatActivity() {

    private lateinit var etDoctorName: EditText
    private lateinit var etDoctorSpeciality: EditText
    private lateinit var etDoctorExperience: EditText
    private lateinit var etDoctorEmail: EditText
    private lateinit var ivDoctorImage: ImageView
    private lateinit var btnUploadImage: Button
    private lateinit var btnSaveDoctor: Button
    private lateinit var progressBar: ProgressBar

    private var selectedImageUri: Uri? = null
    private var doctorId: String = ""
    private var doctorImageBase64: String? = null

    private val firebaseDatabase = FirebaseDatabase.getInstance().getReference("Doctors")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_doctor)

        etDoctorName = findViewById(R.id.etDoctorName)
        etDoctorSpeciality = findViewById(R.id.etDoctorSpeciality)
        etDoctorExperience = findViewById(R.id.etDoctorExperience)
        etDoctorEmail = findViewById(R.id.etDoctorEmail)
        ivDoctorImage = findViewById(R.id.ivDoctorImage)
        btnUploadImage = findViewById(R.id.btnUploadImage)
        btnSaveDoctor = findViewById(R.id.btnSaveDoctor)
        progressBar = findViewById(R.id.progressBar)

        val doctor = intent.getParcelableExtra<Doctor>("doctor")
        doctor?.let {
            doctorId = it.id
            etDoctorName.setText(it.name)
            etDoctorSpeciality.setText(it.speciality)
            etDoctorExperience.setText(it.experience)
            etDoctorEmail.setText(it.email)

            if (!it.imageUrl.isNullOrEmpty()) {
                try {
                    val decodedImage = decodeBase64(it.imageUrl)
                    ivDoctorImage.setImageBitmap(decodedImage)
                    doctorImageBase64 = it.imageUrl
                } catch (e: Exception) {
                    Picasso.get().load(R.drawable.ic_doctor).into(ivDoctorImage)
                }
            }
        }

        btnUploadImage.setOnClickListener {
            openGallery()
        }

        btnSaveDoctor.setOnClickListener {
            updateDoctor()
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

            val inputStream = contentResolver.openInputStream(selectedImageUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            ivDoctorImage.setImageBitmap(bitmap)

            doctorImageBase64 = convertImageToBase64(bitmap)
        }
    }

    private fun convertImageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }

    private fun decodeBase64(base64Str: String): Bitmap {
        val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    private fun updateDoctor() {
        val name = etDoctorName.text.toString().trim()
        val speciality = etDoctorSpeciality.text.toString().trim()
        val experience = etDoctorExperience.text.toString().trim()
        val email = etDoctorEmail.text.toString().trim()

        if (name.isEmpty() || speciality.isEmpty() || experience.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE

        val updatedDoctor = Doctor(doctorId, name, speciality, experience, email, doctorImageBase64 ?: "")

        firebaseDatabase.child(doctorId).setValue(updatedDoctor)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Doctor updated successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, ManageDoctorsActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Failed to update doctor", Toast.LENGTH_SHORT).show()
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
        startActivity(Intent(applicationContext, ManageDoctorsActivity::class.java))
    }
}
