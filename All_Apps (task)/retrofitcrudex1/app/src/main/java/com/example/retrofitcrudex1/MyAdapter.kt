package com.example.retrofitcrudex1

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter(var context: Context, var list: MutableList<Model>): RecyclerView.Adapter<MyView>()
{
    lateinit var apiinterface: Apiinterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.design, parent, false)

        return MyView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textview1.setText(list.get(position).name)
        holder.textview2.setText(list.get(position).surname)
        holder.textview3.setText(list.get(position).email)
        holder.textview4.setText(list.get(position).password)
        holder.itemView.setOnClickListener {

            var alertDialog = AlertDialog.Builder(holder.itemView.context)
            alertDialog.setTitle("Select Operation?")
            alertDialog.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->
                apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)

                var i = Intent(context,UpdateActivity::class.java)
                i.putExtra("id",list.get(position).id)
                i.putExtra("name",list.get(position).name)
                i.putExtra("surname",list.get(position).surname)
                i.putExtra("email",list.get(position).email)
                i.putExtra("password",list.get(position).password)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)





            })
            alertDialog.setNegativeButton("Delete",{ dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(context, ""+list.get(position).id, Toast.LENGTH_SHORT).show()
                apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)
                var call: Call<Void?>? = apiinterface.deletedata(list.get(position).id)
                call!!.enqueue(object: Callback<Void?>
                {
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                        var i = Intent(context,MainActivity2::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(i)
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Toast.makeText(context,"No Internet",Toast.LENGTH_LONG).show()
                    }
                })


            })

            alertDialog.show()


        }

    }

}
class MyView(view: View): RecyclerView.ViewHolder(view)
{
    var textview1:TextView = view.findViewById(R.id.txt1)
    var textview2:TextView = view.findViewById(R.id.txt2)
    var textview3:TextView = view.findViewById(R.id.txt3)
    var textview4:TextView = view.findViewById(R.id.txt4)
}
