package com.example.tutorialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView


class HomeFragment : Fragment() {

    lateinit var gview: GridView
    lateinit var list: MutableList<h_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_home, container, false)

        gview = view.findViewById(R.id.gv)
        list = ArrayList()

        list.add(h_model(R.drawable.android_img,"Android"))
        list.add(h_model(R.drawable.java_img,"Java"))
        list.add(h_model(R.drawable.php_img,"Php"))
        list.add(h_model(R.drawable.ios_img,"ios"))

        var adapter = Home_Adapter(requireActivity(),list)
        gview.adapter = adapter

        gview.setOnItemClickListener { parent, view, position, id ->

            if (position == 0)
            {
                var f2 = AndroidFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }

            if (position == 1)
            {
                var f2 = JavaFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }

            if (position == 2)
            {
                var f2 = PhpFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }

            if (position == 3)
            {
                var f2 = iosFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }
        }

        return view
    }
}