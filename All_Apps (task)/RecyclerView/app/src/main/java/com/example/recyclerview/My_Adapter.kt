package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class My_Adapter(var context: Context, var list: MutableList<Model>):RecyclerView.Adapter<Myview>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        var LayoutInflater = LayoutInflater.from(context)
        var view = LayoutInflater.inflate(R.layout.design,parent, false)

        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {

        holder.image.setImageResource(list.get(position).image)
        holder.text.setText(list.get(position).name)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class Myview(itemView: View):RecyclerView.ViewHolder(itemView)
{
    var image:ImageView
    var text:TextView

    init {
        image = itemView.findViewById(R.id.img)
        text = itemView.findViewById(R.id.txt1)
    }

}
