package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class Status_Adapter(var context: Context, var list: MutableList<Model_Status>):BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size
    }

    override fun getItem(position: Int): Any
    {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        var inflater = LayoutInflater.from(context)
        var View = inflater.inflate(R.layout.design_status,parent, false)

        var img:CircleImageView = View.findViewById(R.id.imgstatus)
        var txt1:TextView = View.findViewById(R.id.txt1status)
        var txt2:TextView = View.findViewById(R.id.txt2status)

        img.setImageResource(list.get(position).imgstatus)
        txt1.setText(list.get(position).txt1status)
        txt2.setText(list.get(position).txt2status)

        return View
    }

}