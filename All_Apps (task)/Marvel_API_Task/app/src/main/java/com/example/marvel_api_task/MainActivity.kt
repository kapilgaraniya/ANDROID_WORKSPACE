package com.example.marvel_api_task

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var  recyclerview: RecyclerView
    lateinit var list: MutableList<Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)
        list = ArrayList()

        var manager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = manager

        var stringrequest = StringRequest(Request.Method.GET, "https://simplifiedcoding.net/demos/marvel/", object : Response.Listener<String>{

            override fun onResponse(response: String?)
            {
                var jsonarray = JSONArray(response)

                for (i in 0 until jsonarray.length())
                {
                    var jsonObject = jsonarray.getJSONObject(i)

                    var name = jsonObject.getString("name")
                    var realname = jsonObject.getString("realname")
                    var team = jsonObject.getString("team")
                    var firstappearance = jsonObject.getString("firstappearance")
                    var createdby = jsonObject.getString("createdby")
                    var publisher = jsonObject.getString("publisher")
                    var bio = jsonObject.getString("bio")
                    var imageurl = jsonObject.getString("imageurl")

                    var m = Model()
                    m.name = name
                    m.realname = realname
                    m.team = team
                    m.firstappearance = firstappearance
                    m.createdby = createdby
                    m.publisher = publisher
                    m.bio = bio
                    m.imageurl = imageurl

                    list.add(m)
                }

                var myAdapter = My_Adapter(applicationContext, list)
                recyclerview.adapter = myAdapter
            }

        },object  : Response.ErrorListener{

            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)
    }
}