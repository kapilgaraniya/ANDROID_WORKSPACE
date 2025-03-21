package com.example.bookmydoc.ui.patient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmydoc.R
import com.example.bookmydoc.data.model.Appointment

class AppointmentAdapter(
    private val appointmentList: List<Appointment>,
    private val onCancelClick: (Appointment) -> Unit
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDoctorName: TextView = view.findViewById(R.id.tvDoctorName)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvTimeSlot: TextView = view.findViewById(R.id.tvTimeSlot)
        val btnCancel: Button = view.findViewById(R.id.btnCancelAppointment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.tvDoctorName.text = "Doctor: ${appointment.doctorName}"
        holder.tvDate.text = "Date: ${appointment.date}"
        holder.tvTimeSlot.text = "Time: ${appointment.timeSlot}"

        holder.btnCancel.setOnClickListener {
            onCancelClick(appointment)
        }
    }

    override fun getItemCount(): Int = appointmentList.size
}
