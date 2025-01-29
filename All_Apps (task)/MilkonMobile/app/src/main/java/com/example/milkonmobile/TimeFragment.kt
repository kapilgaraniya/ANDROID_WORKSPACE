package com.example.milkonmobile

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlin.math.min

class TimeFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var c = Calendar.getInstance()
        var sec = c.get(Calendar.SECOND)
        var min = c.get(Calendar.MINUTE)
        var hour = c.get(Calendar.HOUR_OF_DAY)


        return TimePickerDialog(requireActivity(),this,hour,min, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfday: Int, minute: Int)
    {
        Toast.makeText(requireActivity(), ""+hourOfday+":"+ minute, Toast.LENGTH_SHORT).show()
    }

}