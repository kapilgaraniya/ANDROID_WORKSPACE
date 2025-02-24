package com.example.milkonmobile

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import java.time.Month
import java.time.Year

class DateFragment : androidx.fragment.app.DialogFragment(), DatePickerDialog.OnDateSetListener
{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        var c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_WEEK)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)

        return DatePickerDialog(requireActivity(),this,year,month,day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)
    {
        Toast.makeText(requireActivity(), ""+dayOfMonth+"-"+month+"-"+year, Toast.LENGTH_SHORT).show()
    }

}