package com.example.shreeramcafe

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class test : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // Find the button in the main layout
        val showDialogButton = findViewById<Button>(R.id.showDialogButton)

        // Set the click listener to open the custom dialog
        showDialogButton.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        // Inflate the custom dialog layout
        val dialogView = layoutInflater.inflate(R.layout.d2, null)

        // Create the AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        // Create the dialog and show it
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()

        // Set up the close button functionality
        val btnClose = dialogView.findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
