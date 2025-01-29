package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class StatusFragment : Fragment() {

    lateinit var listview:ListView
    lateinit var list: MutableList<Model_Status>


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_status, container, false)

        listview = view.findViewById(R.id.liststatus)
        list = ArrayList()

        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))
        list.add(Model_Status(R.drawable.u1,"Kapil Garaniya","3:00 pm"))


        var adapter = Status_Adapter(requireActivity(),list)
        listview.adapter = adapter

        listview.setOnItemClickListener { parant, view, position, id ->

            var i = Intent(context,Status_MainActivity3::class.java)

            i.putExtra("pos",position)
            i.putExtra("i",list.get(position).imgstatus)
            i.putExtra("t1",list.get(position).txt1status)
            i.putExtra("t2",list.get(position).txt2status)

            startActivity(i)
        }

        return view
    }


}