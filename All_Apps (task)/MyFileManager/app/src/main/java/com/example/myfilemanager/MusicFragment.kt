package com.example.myfilemanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class MusicFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var list: MutableList<list_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_music, container, false)

        listView = view.findViewById(R.id.listv_m)
        list = ArrayList()

        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))
        list.add(list_model(R.drawable.music_img , "Happy nation", "mp4"))

        var adapter = List_Adapter(requireActivity(),list)
        listView.adapter = adapter

        return view
    }


}