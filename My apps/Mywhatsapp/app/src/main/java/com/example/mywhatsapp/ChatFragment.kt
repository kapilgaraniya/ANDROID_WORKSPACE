package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import java.util.ArrayList

class ChatFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var list: MutableList<Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_chat, container, false)

        listView = view.findViewById(R.id.list)
        list = ArrayList()

        list.add(Model(R.drawable.u1,"A A","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u2,"B B","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u3,"C C","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u4,"D D","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u5,"E E","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u1,"F F","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u2,"G G","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u3,"H H","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u4,"I I","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u5,"J J","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u1,"K K","okay","4:00 pm","9638527410","India"))
        list.add(Model(R.drawable.u2,"L L","okay","4:00 pm","9638527410","India"))


        var adapter = Myadapter2(requireActivity(),list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

            var i = Intent(context, MainActivity2::class.java)

            i.putExtra("pos",position)
            i.putExtra("i",list.get(position).img)
            i.putExtra("n",list.get(position).txt1)
            i.putExtra("p",list.get(position).txt4)
            i.putExtra("c",list.get(position).txt5)

            startActivity(i)

        }

        return  view
    }


}