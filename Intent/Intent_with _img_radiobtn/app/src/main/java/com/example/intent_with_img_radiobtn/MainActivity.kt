package com.example.intent_with_img_radiobtn

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var rollNoEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var browseButton: Button
    private lateinit var selectedImageView: ImageView
    private lateinit var clearButton: Button
    private lateinit var submitButton: Button
    private var selectedImageUri: Uri? = null

    companion object {
        const val REQUEST_IMAGE_PICK = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        rollNoEditText = findViewById(R.id.rollno)
        nameEditText = findViewById(R.id.name)
        cityEditText = findViewById(R.id.city)
        radioGroup = findViewById(R.id.rg)
        browseButton = findViewById(R.id.browsebtn)
        selectedImageView = findViewById(R.id.selectedimg)
        clearButton = findViewById(R.id.clearbtn)
        submitButton = findViewById(R.id.submitbtn)

        // Browse button to open gallery
        browseButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        // Clear button to reset all fields
        clearButton.setOnClickListener {
            rollNoEditText.text.clear()
            nameEditText.text.clear()
            cityEditText.text.clear()
            radioGroup.clearCheck()
            selectedImageView.setImageResource(R.drawable.ic_launcher_foreground)
            selectedImageUri = null
        }

        // Submit button to send data to SecondActivity
        submitButton.setOnClickListener {
            val rollNo = rollNoEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()
            val city = cityEditText.text.toString().trim()

            if (rollNo.isEmpty() || name.isEmpty() || city.isEmpty()) {
                Toast.makeText(this, "Please enter all details!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Please select gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
            val gender = selectedRadioButton.text.toString()

            if (selectedImageUri == null) {
                Toast.makeText(this, "Please select an image!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("ROLL_NO", rollNo)
                putExtra("NAME", name)
                putExtra("CITY", city)
                putExtra("GENDER", gender)
                putExtra("IMAGE_URI", selectedImageUri.toString())
            }

            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            selectedImageView.setImageURI(selectedImageUri)
        }
    }
}