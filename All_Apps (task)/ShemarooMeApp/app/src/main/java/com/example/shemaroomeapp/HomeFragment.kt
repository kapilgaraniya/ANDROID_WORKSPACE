package com.example.shemaroomeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment()
{
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_home, container, false)


        viewPager = view.findViewById(R.id.viewpager)
        setviewpager()

        tabLayout = view.findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)


        return view
    }

    private fun setviewpager() {
        var adapter = MyAdapter(requireActivity(), requireFragmentManager())
        var a = "data"
        adapter.tops("All",AllFragment())
        adapter.tops("Moive",MoviesFragment())
        adapter.tops("Shows",ShowsFragment())
        adapter.tops("Channels",ChannelsFragment())
        adapter.tops("Live",LiveFragment())
        adapter.tops("Channels",ChannelsFragment())
        adapter.tops("Live",LiveFragment())


        viewPager.adapter = adapter
    }

}