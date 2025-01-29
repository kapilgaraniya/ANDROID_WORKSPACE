package com.example.myfilemanager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class List_Adapter(var context: Context, var list: MutableList<list_model>): BaseAdapter()
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
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var Layout = LayoutInflater.from(context)
        var view = Layout.inflate(R.layout.list_disign, parent, false)

        var img:ImageView = view.findViewById(R.id.md_img)
        var txt1:TextView = view.findViewById(R.id.md_txt1)
        var txt2:TextView = view.findViewById(R.id.md_txt2)

        img.setImageResource(list.get(position).img)
        txt1.setText(list.get(position).txt1)
        txt2.setText(list.get(position).txt2)

        return view
    }

}