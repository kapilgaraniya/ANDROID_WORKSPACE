package com.example.milkonmobile

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(var context: Context, var list: MutableList<Model>):BaseAdapter()
{
    override fun getCount(): Int {
        return  list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design, parent, false)

        var txt1:TextView = view.findViewById(R.id.txt1)
        var img:ImageView = view.findViewById(R.id.img)
        var txt2:TextView = view.findViewById(R.id.txt2)

        txt1.setText(list.get(position).txt1)
        txt2.setText(list.get(position).txt2)
        img.setImageResource(list.get(position).img1)



        return view
    }

}