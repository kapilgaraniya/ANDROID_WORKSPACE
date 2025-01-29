package com.example.myfilemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class DocumentFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var list: MutableList<list_model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_document, container, false)

        listView = view.findViewById(R.id.listv_d)
        list = ArrayList()

        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))
        list.add(list_model(R.drawable.doc_img , "kapilGaraniya Resume", "2 mb"))

        var adapter = List_Adapter(requireActivity(),list)
        listView.adapter = adapter


        return view
    }


}