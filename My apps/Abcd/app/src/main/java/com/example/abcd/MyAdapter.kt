package com.example.abcd

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MyAdapter(var context: Context, var list: MutableList<Model>):BaseAdapter
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.disign,parent,false)

        var btn:

        return view

    }

}