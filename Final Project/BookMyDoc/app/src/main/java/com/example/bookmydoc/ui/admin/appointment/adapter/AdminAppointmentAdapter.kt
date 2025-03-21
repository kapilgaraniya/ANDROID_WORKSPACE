package com.example.bookmydoc.ui.admin.appointment.adapter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.SharedPreferences
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmydoc.R
import com.example.bookmydoc.data.model.Appointment
import com.google.firebase.database.FirebaseDatabase

class AdminAppointmentAdapter(
    private val context: Context,
    private val appointmentList: MutableList<Appointment>,
    private val onCancelAppointment: (Appointment) -> Unit
) : RecyclerView.Adapter<AdminAppointmentAdapter.AppointmentViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Appointments", Context.MODE_PRIVATE)

    inner class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDoctorName: TextView = view.findViewById(R.id.tvDoctorName)
        val tvPatientName: TextView = view.findViewById(R.id.tvPatientName)
        val tvPatientContact: TextView = view.findViewById(R.id.tvPatientContact)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvTimeSlot: TextView = view.findViewById(R.id.tvTimeSlot)
        val btnConfirm: Button = view.findViewById(R.id.btnConfirm)
        val btnDelete: Button = view.findViewById(R.id.btnDeleteAppointment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_admin_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.tvDoctorName.text = appointment.doctorName
        holder.tvPatientName.text = appointment.patientName
        holder.tvPatientContact.text = appointment.patientContact
        holder.tvDate.text = appointment.date
        holder.tvTimeSlot.text = appointment.timeSlot

        val isConfirmed = sharedPreferences.getBoolean(appointment.appointmentId, false)
        if (isConfirmed) {
            holder.btnConfirm.text = "Confirmed"
            holder.btnConfirm.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            holder.btnConfirm.isEnabled = false
        } else {
            holder.btnConfirm.text = "Confirm Appointment"
            holder.btnConfirm.setBackgroundColor(ContextCompat.getColor(context, R.color.primary))
            holder.btnConfirm.isEnabled = true
        }

        holder.btnDelete.setOnClickListener {
            onCancelAppointment(appointment) // Calling the cancellation function passed from the activity
        }

        holder.btnConfirm.setOnClickListener {
            sendSms(appointment.patientContact, appointment)
            sharedPreferences.edit().putBoolean(appointment.appointmentId, true).apply()
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = appointmentList.size

    private fun sendSms(patientContact: String, appointment: Appointment) {
        if (patientContact.isBlank() || patientContact == "N/A") {
            return
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val message =
            "Dear ${appointment.patientName}, your appointment with ${appointment.doctorName} is confirmed on ${appointment.date} at ${appointment.timeSlot}. Please arrive on time. Thank you!"

        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(patientContact, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
