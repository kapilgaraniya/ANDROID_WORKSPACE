package com.example.mywhatsapp

import android.content.Context
import android.service.quicksettings.Tile
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyAdapter(context: Context, fm:FragmentManager) :FragmentStatePagerAdapter(fm)
{
    var listfragment:ArrayList<Fragment> = ArrayList()
    var listtitle:ArrayList<String> = ArrayList()

    override fun getCount(): Int {

        return listfragment.size
    }

    override fun getItem(position: Int): Fragment {

        return listfragment.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return listtitle.get(position)
    }

    fun tops(tile:String, fragment: Fragment)
    {
        listtitle.add(tile)
        listfragment.add(fragment)
    }

}