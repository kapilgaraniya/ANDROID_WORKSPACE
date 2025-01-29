package com.example.relam_ex

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.realm.Realm

class ViewActivity : AppCompatActivity(), AdapterView.OnItemLongClickListener {

    lateinit var listview: ListView
    lateinit var list:MutableList<Model>
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view)

        listview = findViewById(R.id.list)
        list = ArrayList()
        realm = Realm.getInstance(Realm.getDefaultConfiguration())


        realm.beginTransaction()

        var realmresults = realm.where(Model::class.java).findAll()

        for (i in realmresults.indices)
        {
            list.add(realmresults[i]!!)
        }

        var adapter = MyAdapter(applicationContext,list)
        listview.adapter=adapter

        realm.commitTransaction()

        listview.setOnItemLongClickListener(this)


    }

    @SuppressLint("MissingInflatedId")
    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {

        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Select Operations?")
        alertDialog.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->


            var alert2 = AlertDialog.Builder(this)
            var inflater= LayoutInflater.from(this)
            var view =inflater.inflate(R.layout.activity_update,null)
            var edit1: EditText =view.findViewById(R.id.edtname)
            var edit2:EditText =view.findViewById(R.id.edtnum)

            edit1.setText(list.get(position).name)
            edit2.setText(list.get(position).num)
            alert2.setView(view)
            alert2.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->

                realm.beginTransaction()
                var name = edit1.text.toString()
                var num = edit2.text.toString()

                list.get(position).name =name
                list.get(position).num =num

                realm.commitTransaction()
                startActivity(Intent(this,ViewActivity::class.java))



            })


            alert2.show()

        })
        alertDialog.setNegativeButton("Delete",{ dialogInterface: DialogInterface, i: Int ->

            realm.beginTransaction()

            var deleterealm = realm.where(Model::class.java).findAll()
            deleterealm.deleteFromRealm(position)
            realm.commitTransaction()
            startActivity(Intent(applicationContext,ViewActivity::class.java))


        })
        alertDialog.show()


        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()

    var i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)

    }
}