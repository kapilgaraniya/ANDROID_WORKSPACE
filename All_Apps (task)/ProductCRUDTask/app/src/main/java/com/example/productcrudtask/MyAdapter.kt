package com.example.productcrudtask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context, var list: MutableList<Model>): BaseAdapter()
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
        var view =layout.inflate(R.layout.design,parent,false)

        var pimg:ImageView = view.findViewById(R.id.img)
        var pname:TextView = view.findViewById(R.id.txt1)
        var pprice:TextView = view.findViewById(R.id.txt2)
        var pstatus:TextView = view.findViewById(R.id.txt3)
        var pdesc:TextView = view.findViewById(R.id.txt4)

        Picasso.get().load(list.get(position).pimage).into(pimg)
        pname.setText(list.get(position).pname)
        pprice.setText(list.get(position).pprice)
        pstatus.setText(list.get(position).pstatus)
        pdesc.setText(list.get(position).pdesc)

        return view
    }

}