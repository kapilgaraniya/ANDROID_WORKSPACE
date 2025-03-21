package com.example.bookmydoc.ui.admin.patient.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmydoc.data.model.Patient
import com.example.bookmydoc.databinding.ItemPatientBinding

class PatientAdapter(
    private val patients: ArrayList<Patient>,
    private val onDeleteClick: (Patient) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    class PatientViewHolder(val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.binding.txtFullName.text = patient.fullName
        holder.binding.txtPhone.text = patient.phone
        holder.binding.txtEmail.text = patient.email
        holder.binding.txtAddress.text = patient.address
        holder.binding.txtPassword.text = patient.password  // Only if necessary

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(patient)
        }
    }


    override fun getItemCount(): Int = patients.size
}