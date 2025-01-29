package com.example.productcrudtask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class LtoHFragment : Fragment() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    { // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_lto_h, container, false)

        listView = view.findViewById(R.id.glist_ltoh)
        list = ArrayList()

        var stringRequest = StringRequest(
            Request.Method.GET,"https://prakrutitech.buzz/Kapil/filterasc.php",
            object: Response.Listener<String>
            {
                override fun onResponse(response: String?) {
                    var jsonarray = JSONArray(response)
                    for (i in 0 until jsonarray.length())
                    {
                        var jsonObject = jsonarray.getJSONObject(i)

                        var name = jsonObject.getString("p_name")
                        var price = jsonObject.getString("p_price")
                        var status = jsonObject.getString("p_status")
                        var desc = jsonObject.getString("p_desc")
                        var image = jsonObject.getString("p_image")

                        var m = Model()
                        m.pname = name
                        m.pprice = price
                        m.pstatus = status
                        m.pdesc = desc
                        m.pimage = image

                        list.add(m)
                    }

                    var myadapter = MyAdapter(requireActivity(),list)
                    myadapter.notifyDataSetChanged()
                    listView.adapter = myadapter
                }
            },
            object : Response.ErrorListener
            {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(requireActivity(), "No Internet", Toast.LENGTH_SHORT).show()
                }

            }
        )

        var queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(stringRequest)


        return view
    }

}