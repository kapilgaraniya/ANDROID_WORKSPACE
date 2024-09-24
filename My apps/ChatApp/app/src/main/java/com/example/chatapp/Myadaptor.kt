package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Myadaptor(var context: Context, var list: MutableList<Modal>):BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size
    }

    override fun getItem(position: Int): Any
    {
        return list[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View
    {
        var layout = LayoutInflater.from(context)
        var view =layout.inflate(R.layout.design, parent, false)

        var img:ImageView = view.findViewById(R.id.img)
        var txt1:TextView = view.findViewById((R.id.txt1))
        var txt2:TextView = view.findViewById((R.id.txt2))
        var txt3:TextView = view.findViewById((R.id.txt3))

        img.setImageResource(list.get(position).img)
        txt1.setText(list.get(position).txt1)
        txt2.setText(list.get(position).txt2)
        txt3.setText(list.get(position).txt3)

        return view
    }
}
