package com.example.productadminapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context, var list: MutableList<Model>) : BaseAdapter()
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design, parent, false)

        var pimg:ImageView = view.findViewById(R.id.img)
        var txt1:TextView = view.findViewById(R.id.txt1)
        var txt2:TextView = view.findViewById(R.id.txt2)
        var txt3:TextView = view.findViewById(R.id.txt3)
        var txt4:TextView = view.findViewById(R.id.txt4)

        Picasso.get().load(list.get(position).pimg).into(pimg)
        txt1.setText(list.get(position).pname)
        txt2.setText(list.get(position).pprice)
        txt3.setText(list.get(position).pstatus)
        txt4.setText(list.get(position).pdesc)

        return view
    }

}