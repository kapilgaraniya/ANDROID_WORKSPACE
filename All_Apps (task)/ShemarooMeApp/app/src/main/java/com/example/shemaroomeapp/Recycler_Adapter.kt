package com.example.shemaroomeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class Recycler_Adapter(var context: Context?, var list: MutableList<All_Model>):RecyclerView.Adapter<Myview>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        var LayoutInflater = LayoutInflater.from(context)
        var view = LayoutInflater.inflate(R.layout.recycler_design,parent, false)

        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {

       holder.image.setImageResource(list.get(position).image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class Myview(itemView: View):RecyclerView.ViewHolder(itemView)
{
    var image:ImageView

    init {
        image = itemView.findViewById(R.id.img)
    }

}