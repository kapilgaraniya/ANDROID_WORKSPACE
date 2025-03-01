package com.example.myfilemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView


class VideoFragment : Fragment() {

    lateinit var gridView: GridView
    lateinit var list1: MutableList<grid_model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_video, container, false)

        gridView = view.findViewById(R.id.gridv_v)
        list1 = ArrayList()

        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))
        list1.add(grid_model(R.drawable.galary_img , "Camera", "11"))


        var adapter = Grid_Adapter(requireActivity(),list1)
        gridView.adapter = adapter

        return view
    }


}