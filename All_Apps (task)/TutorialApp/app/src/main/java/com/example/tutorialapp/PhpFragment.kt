package com.example.tutorialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class PhpFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: MutableList<A_model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_php, container, false)

        listView = view.findViewById(R.id.php_listview)
        list = ArrayList()

        list.add(A_model("What is PHP?\n","PHP (Hypertext Preprocessor) is a widely-used open-source server-side scripting language designed specifically for web development. It is embedded within HTML and is particularly suited for creating dynamic and interactive websites. PHP scripts are executed on the server, generating HTML that is sent to the client's browser, allowing for the creation of personalized and data-driven web pages."))
        list.add(A_model("Key Features of PHP:\n","Ease of Use: PHP is relatively easy to learn for beginners, with a syntax that is similar to other programming languages like C and Java.\n" +
                "Flexibility: It can be embedded directly into HTML, making it seamless to integrate with web content.\n" +
                "Extensive Community Support: A large community contributes to a vast repository of libraries, frameworks, and tools.\n" +
                "Cross-Platform Compatibility: PHP runs on various operating systems, including Windows, Linux, and macOS.\n" +
                "Database Integration: PHP easily integrates with numerous databases, such as MySQL, PostgreSQL, and SQLite.\n" +
                "Scalability: Suitable for both small-scale websites and large, complex web applications."))
        list.add(A_model("Common Uses of PHP:\n","Dynamic Web Page Creation: Generating content that changes based on user interaction or other factors.\n" +
                "Form Handling: Collecting and processing data submitted through web forms.\n" +
                "Session Management: Managing user sessions for login systems and personalized experiences.\n" +
                "Content Management Systems (CMS): Powering popular CMS platforms like WordPress, Drupal, and Joomla.\n" +
                "E-commerce Platforms: Building and managing online stores and transactional systems."))

        var adapter =A_Adapter(requireActivity(),list)
        listView.adapter = adapter

        return view
    }

}