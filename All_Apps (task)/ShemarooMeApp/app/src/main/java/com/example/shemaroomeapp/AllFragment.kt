package com.example.shemaroomeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView

class AllFragment : Fragment() {

    lateinit var sliderLayout: SliderLayout
    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<All_Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_all, container, false)

        sliderLayout = view.findViewById(R.id.sliderLayout)
        var map = HashMap<String,Int>()

        map.put("a",R.drawable.hanuman_slider)
        map.put("b",R.drawable.beta_movie_slider)
        map.put("c",R.drawable.ghatothkach_slideer)
        map.put("d",R.drawable.hanuman_slider)
        map.put("e",R.drawable.beta_movie_slider)
        map.put("f",R.drawable.ghatothkach_slideer)
        map.put("g",R.drawable.hanuman_slider)

        for (i in map.keys)
        {
            var textslider = TextSliderView(context)
            textslider.image(map.get(i)!!)

            sliderLayout.addSlider(textslider)
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipPage)
        //--------------------------------------------------------------------

        recyclerView = view.findViewById(R.id.recycaler)
        list = ArrayList()


        var manager:RecyclerView.LayoutManager = GridLayoutManager(requireActivity(),6)
        recyclerView.layoutManager = manager

        list.add(All_Model(R.drawable.hanuman_slider))
        list.add(All_Model(R.drawable.beta_movie_slider))
        list.add(All_Model(R.drawable.ghatothkach_slideer))
        list.add(All_Model(R.drawable.hanuman_slider))
        list.add(All_Model(R.drawable.beta_movie_slider))
        list.add(All_Model(R.drawable.ghatothkach_slideer))

        var adapter = Recycler_Adapter(context, list)
        recyclerView. adapter = adapter

        return view
    }

}