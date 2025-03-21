package com.example.bookmydoc.ui.admin.doctor.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bookmydoc.data.Doctor
import com.example.bookmydoc.R
import com.example.bookmydoc.ui.admin.doctor.AddDoctorActivity
import com.example.bookmydoc.ui.admin.doctor.EditDoctorActivity
import com.example.bookmydoc.ui.admin.doctor.ManageDoctorsActivity
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class DoctorAdapter(
    private val context: Context,
    private val doctorList: MutableList<Doctor>,
    private val onEditDoctor: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorImage: ImageView = view.findViewById(R.id.ivDoctorImage)
        val doctorName: TextView = view.findViewById(R.id.tvDoctorName)
        val doctorSpeciality: TextView = view.findViewById(R.id.tvDoctorSpeciality)
        val btnEdit: ImageView = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.doctorName.text = doctor.name
        holder.doctorSpeciality.text = doctor.speciality

        if (!doctor.imageUrl.isNullOrEmpty()) {
            try {
                val decodedImage = decodeBase64(doctor.imageUrl)
                holder.doctorImage.setImageBitmap(decodedImage)
            } catch (e: Exception) {
                Picasso.get().load(R.drawable.ic_doctor).into(holder.doctorImage)
            }
        } else {
            holder.doctorImage.setImageResource(R.drawable.ic_doctor)
        }

        holder.btnEdit.setOnClickListener {
            onEditDoctor(doctor)
        }

        holder.btnDelete.setOnClickListener {


            showDeleteConfirmationDialog(doctor, position, holder)
        }
    }

    private fun showDeleteConfirmationDialog(doctor: Doctor, position: Int, holder: DoctorViewHolder) {
        val alert = AlertDialog.Builder(holder.btnDelete.context)
        alert.setTitle("Are you sure you want to delete this doctor?")
        alert.setPositiveButton("Yes") { _, _ ->
            deleteDoctor(doctor, position)

        }
        alert.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        alert.show()
    }

    private fun deleteDoctor(doctor: Doctor, position: Int) {
        val doctorId = doctor.id
        if (!doctorId.isNullOrEmpty()) {
            FirebaseDatabase.getInstance().getReference("Doctors")
                .child(doctorId)
                .removeValue()
                .addOnSuccessListener {
                    if (position < doctorList.size) {
                        doctorList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, doctorList.size)
                    } else {
                        notifyDataSetChanged()
                    }

                    Toast.makeText(context, "Doctor deleted successfully", Toast.LENGTH_SHORT).show()

                    if (context is ManageDoctorsActivity) {
                        (context as ManageDoctorsActivity).refreshDoctorList()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to delete doctor", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Doctor ID is invalid", Toast.LENGTH_SHORT).show()
        }
    }



    private fun decodeBase64(base64Str: String): Bitmap {
        val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    override fun getItemCount(): Int = doctorList.size
}
