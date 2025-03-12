package com.example.bookmydoc.ui.patient.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bookmydoc.data.Doctor
import com.example.bookmydoc.R
import com.google.android.material.imageview.ShapeableImageView
import java.io.ByteArrayInputStream

class PatientDoctorAdapter(
    private val context: Context,
    private val doctorList: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit, // New click listener for item click
    private val onBookClick: (Doctor) -> Unit  // Click listener for button
) : RecyclerView.Adapter<PatientDoctorAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorImg: ShapeableImageView = view.findViewById(R.id.ivDoctorImg)
        val doctorName: TextView = view.findViewById(R.id.tvDoctorName)
        val doctorSpeciality: TextView = view.findViewById(R.id.tvDoctorSpeciality)
        val tvDoctorRating: TextView = view.findViewById(R.id.tvDoctorRating)
        val btnBook: Button = view.findViewById(R.id.btnBookAppointment)

        init {
            view.setOnClickListener {
                onDoctorClick(doctorList[adapterPosition])
            }
            btnBook.setOnClickListener {
                onBookClick(doctorList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_doctor_booking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctorList[position]

        holder.doctorName.text = doctor.name
        holder.doctorSpeciality.text = doctor.speciality

        val stars = "⭐".repeat(doctor.averageRating.toInt())
        holder.tvDoctorRating.text = String.format("%.1f %s", doctor.averageRating, stars)

        // Convert Base64 string to Bitmap
        if (!doctor.imageUrl.isNullOrEmpty()) {
            val bitmap = decodeBase64ToBitmap(doctor.imageUrl)
            holder.doctorImg.setImageBitmap(bitmap ?: BitmapFactory.decodeResource(context.resources, R.drawable.ic_doctor))
        } else {
            holder.doctorImg.setImageResource(R.drawable.ic_doctor)
        }
    }

    override fun getItemCount(): Int = doctorList.size

    private fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }
}
