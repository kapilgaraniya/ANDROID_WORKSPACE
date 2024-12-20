package com.example.q1todoapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class DisignFragment : Fragment()
{

    lateinit var listView: ListView
    lateinit var list: MutableList<Model>
    var arrayList = ArrayList<HashMap<String, String>>()
    lateinit var dbhelper: Dbhelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_disign, container, false)

        dbhelper = Dbhelper(requireActivity())
        listView = view.findViewById(R.id.list)
        list = dbhelper.viewdata()

        Toast.makeText(requireActivity(), "Items: ${list.size}", Toast.LENGTH_SHORT).show()

        for (item in list) {
            val hm = HashMap<String, String>()
            hm["n1"] = item.Tname
            hm["n2"] = item.Tdisc
            arrayList.add(hm)
        }

        val fromArray = arrayOf("n1", "n2")
        val toArray = intArrayOf(R.id.txtname, R.id.txtdisc)

        val adapter = SimpleAdapter(requireActivity(), arrayList, R.layout.design, fromArray, toArray)
        listView.adapter = adapter

        registerForContextMenu(listView)

        return view
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        val update = menu.add(0, 1, 0, "Update")
        val delete = menu.add(0, 2, 0, "Delete")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        val acm = item.menuInfo as AdapterContextMenuInfo
        val position = acm.position
        val selectedItemId = list[position].id

        when (item.itemId)
        {
            1 ->
                {

                    var intent = Intent(requireActivity(), UpdateActivity::class.java).apply{

                    putExtra("id", selectedItemId)
                    putExtra("name", list[position].Tname)
                    putExtra("num", list[position].Tdisc)
                }
                startActivity(intent)
            }

            2 ->
                {

                    var alertDialog = AlertDialog.Builder(requireContext())
                    .setTitle("Are You Sure You Want to Delete?")
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                        dbhelper.deletedata(selectedItemId)
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                    }
                    .setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.cancel()
                    }
                    .create()
                    alertDialog.show()
                }
        }
        return super.onContextItemSelected(item)
    }

}
