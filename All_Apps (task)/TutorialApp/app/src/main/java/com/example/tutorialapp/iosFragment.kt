package com.example.tutorialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class iosFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: MutableList<A_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_ios, container, false)

        listView = view.findViewById(R.id.ios_listview)
        list = ArrayList()

        list.add(A_model("Introduction to iOS Development:\n","iOS development involves creating applications for Apple's mobile operating system, iOS, which runs on iPhone and iPad devices. Development is typically done using Xcode, Apple's integrated development environment (IDE), and programming is primarily done in Swift, Apple's modern programming language."))
        list.add(A_model("Why Choose iOS Development?\n","Market Reach: iOS devices hold a significant market share, especially in regions like North America and Western Europe.\n" +
                "Revenue Potential: iOS users are generally more willing to spend on apps, offering better monetization opportunities.\n" +
                "Consistent Hardware and Software: Apple’s controlled ecosystem ensures uniform hardware and software standards, simplifying development and testing.\n" +
                "Access to Cutting-Edge Technologies: iOS provides developers with early access to new technologies and frameworks."))
        list.add(A_model("iOS Development Ecosystem\n","Xcode: The official Integrated Development Environment (IDE) for iOS development, offering tools for coding, designing, testing, and debugging.\n" +
                "Swift & Objective-C: Primary programming languages used for iOS development, with Swift being the modern, preferred choice.\n" +
                "App Store: The platform for distributing iOS applications to millions of users worldwide."))

        var adapter =A_Adapter(requireActivity(),list)
        listView.adapter = adapter

        return view
    }

}