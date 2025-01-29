package com.example.productcrudtask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class AllFragment : Fragment() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>
    lateinit var searchView: SearchView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all, container, false)

        listView = view.findViewById(R.id.glist)
        searchView = view.findViewById(R.id.search)
        list = ArrayList()

        loadProducts("") // Initial load without filter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadProducts(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadProducts(newText.orEmpty())
                return true
            }
        })

        return view
    }

    private fun loadProducts(query: String) {
        val url = "https://prakrutitech.buzz/Kapil/search.php?query=$query"


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                list.clear()
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

                    val name = jsonObject.getString("p_name")
                    val price = jsonObject.getString("p_price")
                    val status = jsonObject.getString("p_status")
                    val desc = jsonObject.getString("p_desc")
                    val image = jsonObject.getString("p_image")

                    val m = Model().apply {
                        pname = name
                        pprice = price
                        pstatus = status
                        pdesc = desc
                        pimage = image
                    }

                    list.add(m)
                }

                val myAdapter = MyAdapter(requireActivity(), list)
                myAdapter.notifyDataSetChanged()
                listView.adapter = myAdapter
            },
            {
                Toast.makeText(activity, "No Internet", Toast.LENGTH_SHORT).show()
            }
        )

        val queue: RequestQueue = Volley.newRequestQueue(activity)
        queue.add(stringRequest)
    }
}
