package com.bookmydoc.data.remote

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage

object FirebaseStorageHelper {
    fun uploadDoctorImage(doctorId: String, imageUri: Uri, callback: (String?) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("doctor_images/$doctorId.jpg")

        storageRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    Log.d("FirebaseStorageHelper", "Image uploaded successfully: $uri")
                    callback(uri.toString()) // Return the image URL
                }.addOnFailureListener { e ->
                    Log.e("FirebaseStorageHelper", "Failed to get download URL: ${e.message}")
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseStorageHelper", "Image upload failed: ${e.message}")
                callback(null)
            }
    }
}
