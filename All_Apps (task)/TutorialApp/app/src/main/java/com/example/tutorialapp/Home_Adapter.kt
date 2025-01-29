package com.example.tutorialapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Home_Adapter(var context: Context, var list: MutableList<h_model>): BaseAdapter()
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convetView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.home_design,parent,false)

        var img1: ImageView = view.findViewById(R.id.logoimg)
        var txt1: TextView = view.findViewById(R.id.d_txt)

        img1.setImageResource(list.get(position).img)
        txt1.setText(list.get(position).txt)

        return view

    }

}