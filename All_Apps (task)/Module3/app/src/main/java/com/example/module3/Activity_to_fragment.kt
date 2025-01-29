package com.example.module3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Activity_to_fragment : Fragment() {

    lateinit var txt1:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_activity_to_fragment, container, false)

        txt1 = view.findViewById(R.id.abcd)

        txt1.setOnClickListener {

            var i = Intent(activity, Que6::class.java)
            startActivity(i)
        }

        return view
    }

}