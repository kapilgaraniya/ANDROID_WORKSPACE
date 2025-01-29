package com.example.tutorialapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class A_Adapter(var context: Context, var A_list:MutableList<A_model>): BaseAdapter()
{
    override fun getCount(): Int {
        return A_list.size
    }

    override fun getItem(position: Int): Any {
        return A_list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var Layout = LayoutInflater.from(context)
        var view = Layout.inflate(R.layout.design,parent,false)

        var txt1: TextView = view.findViewById(R.id.txtheading)
        var txt2: TextView = view.findViewById(R.id.txtsummary)

        txt1.setText(A_list.get(position).txt_heading)
        txt2.setText(A_list.get(position).txt_summary)


        return view
    }

}