package com.example.tutorialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class JavaFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: MutableList<A_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_java, container, false)

        listView = view.findViewById(R.id.java_listview)
        list = ArrayList()

        list.add(A_model("What is Java?\n\n","Java is a popular programming language, created in 1995.\n" +
                "\n" +
                "It is owned by Oracle, and more than 3 billion devices run Java.\n" +
                "\n" +
                "It is used for:\n" +
                "\n" +
                "Mobile applications (specially Android apps)\n" +
                "Desktop applications\n" +
                "Web applications\n" +
                "Web servers and application servers\n" +
                "Games\n" +
                "Database connection\n" +
                "And much, much more!\n"))
        list.add(A_model("Why Use Java?\n","Java works on different platforms (Windows, Mac, Linux, Raspberry Pi, etc.)\n" +
                "It is one of the most popular programming languages in the world\n" +
                "It has a large demand in the current job market\n" +
                "It is easy to learn and simple to use\n" +
                "It is open-source and free\n" +
                "It is secure, fast and powerful\n" +
                "It has huge community support (tens of millions of developers)\n" +
                "Java is an object oriented language which gives a clear structure to programs and allows code to be reused, lowering development costs\n" +
                "As Java is close to C++ and C#, it makes it easy for programmers to switch to Java or vice versa"))
        list.add(A_model("Java Install\n","Some PCs might have Java already installed.\n" +
                "\n" +
                "To check if you have Java installed on a Windows PC, search in the start bar for Java or type the following in Command Prompt (cmd.exe):"))


        var adapter =A_Adapter(requireActivity(),list)
        listView.adapter = adapter

        return view
    }
}