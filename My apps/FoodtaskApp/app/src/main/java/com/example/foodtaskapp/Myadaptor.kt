package com.example.foodtaskapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Myadaptor(var Context:Context,var list: MutableList<Model>):BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size
    }

    override fun getItem(p0: Int): Any
    {
        return p0
    }

    override fun getItemId(p0: Int): Long
    {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
    {
        var layout = LayoutInflater.from(Context)
        var view = layout.inflate(R.layout.design,p2,false)

        var img:ImageView = view.findViewById(R.id.img)
        var txt:TextView = view.findViewById(R.id.txt)


        img.setImageResource(list.get(p0).img)
        txt.setText(list.get(p0).txt)

        return view
    }

}

