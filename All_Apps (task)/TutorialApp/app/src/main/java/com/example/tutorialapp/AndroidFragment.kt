package com.example.tutorialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class AndroidFragment : Fragment()
{
    lateinit var listView: ListView
    lateinit var list: MutableList<A_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_android, container, false)

        listView = view.findViewById(R.id.android_listview)
        list = ArrayList()

        list.add(A_model("Android Tutorial","Android can be simply understood as a software package. To elaborate more, Android is a Linux based operating system for mobile devices. Mobile devices can be tablet computers and smartphones or any other similar devices. First developed by Google, it was later developed by the Open Handset Alliance (OHA). Various languages can be used to write the android code but mainly Java is used to serve this purpose. To improve the mobile experience for the end-users, the android is used to create a successful real-world product. Android has many code names such as Lollipop, Kitkat, Jelly Bean, Ice cream Sandwich, Froyo, Eclair, Donut, and many more."))
        list.add(A_model("Features of Android:","Android has many important features. Some of these features are listed below:\n" + "\n"+"Android is an open-source platform.\n" +
                "The Android platform can be customized by anyone.\n" +
                "The consumers have the facility to choose from the multiple options of mobile applications available in the market.\n" +
                "Features like weather details, opening screen, live RSS (Really Simple Syndication) feeds, etc, are interesting to have and are thus supported and facilitated by Android.\n" +
                "Vital features like messaging services(SMS and MMS), web browser, storage (SQLite), connectivity (GSM, CDMA, BlueTooth, Wi-Fi, etc.), media, handset layout, etc are also supported and facilitated by Android."))
        list.add(A_model("Categories of Android applications:","The top categories of android applications available in the market are listed below:\n" +
                "\n" +
                "Entertainment\n" +
                "Tools\n" +
                "Communication\n" +
                "Productivity\n" +
                "Personalization\n" +
                "Music and Audio\n" +
                "Social\n" +
                "Media and Video\n" +
                "Travel and Local\n" +
                "There are many more categories available in the market than those mentioned above."))

        var adapter = A_Adapter(requireActivity(),list)
        listView.adapter = adapter

        return view
    }
}