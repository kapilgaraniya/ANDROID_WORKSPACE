package com.example.myfilemanager

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Grid_Adapter(var context: Context, var grid:MutableList<grid_model>): BaseAdapter()
{
    override fun getCount(): Int {
        return grid.size
    }

    override fun getItem(position: Int): Any {
        return grid.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convetView: View?, parent: ViewGroup?): View {

        var Layout = LayoutInflater.from(context)
        var view = Layout.inflate(R.layout.grid_design, parent, false)

        var img: ImageView = view.findViewById(R.id.iv_img)
        var txt1: TextView = view.findViewById(R.id.iv_txt1)
        var txt2: TextView = view.findViewById(R.id.iv_txt2)

        img.setImageResource(grid.get(position).img)
        txt1.setText(grid.get(position).txt1)
        txt2.setText(grid.get(position).txt2)

        return view
    }

}