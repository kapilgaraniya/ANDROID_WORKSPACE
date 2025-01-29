package com.example.api_first_ex

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var  listView: ListView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()

        var stringrequest = StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/photos", object :Response.Listener<String>{

            override fun onResponse(response: String?)
            {
                var jsonarray = JSONArray(response)

                for (i in 0 until jsonarray.length())
                {
                    var jsonObject = jsonarray.getJSONObject(i)

                    var title = jsonObject.getString("title")
                    var thumbnailurl = jsonObject.getString("thumbnailUrl")

                    var m = Model()
                    m.title = title
                    m.image = thumbnailurl

                    list.add(m)
                }

                var myAdapter = MyAdapter(applicationContext, list)
                listView.adapter = myAdapter
            }

        },object :Response.ErrorListener{

            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)

    }
}