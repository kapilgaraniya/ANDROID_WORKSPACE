package com.example.marvel_api_task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class My_Adapter(var context: Context, var list: MutableList<Model>):RecyclerView.Adapter<Myview>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        var LayoutInflater = LayoutInflater.from(context)
        var view = LayoutInflater.inflate(R.layout.design,parent, false)

        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {

        holder.text1.setText(list.get(position).name)
        holder.text2.setText(list.get(position).realname)
        holder.text3.setText(list.get(position).team)
        holder.text4.setText(list.get(position).firstappearance)
        holder.text5.setText(list.get(position).createdby)
        holder.text6.setText(list.get(position).publisher)
        Picasso.get().load(list.get(position).imageurl).into(holder.image)
        holder.text7.setText(list.get(position).bio)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class Myview(itemView: View):RecyclerView.ViewHolder(itemView)
{
    var image:ImageView
    var text1:TextView
    var text2:TextView
    var text3:TextView
    var text4:TextView
    var text5:TextView
    var text6:TextView
    var text7:TextView

    init {
        image = itemView.findViewById(R.id.img)
        text1 = itemView.findViewById(R.id.txt1)
        text2 = itemView.findViewById(R.id.txt2)
        text3 = itemView.findViewById(R.id.txt3)
        text4 = itemView.findViewById(R.id.txt4)
        text5 = itemView.findViewById(R.id.txt5)
        text6 = itemView.findViewById(R.id.txt6)
        text7 = itemView.findViewById(R.id.txt7)
    }

}