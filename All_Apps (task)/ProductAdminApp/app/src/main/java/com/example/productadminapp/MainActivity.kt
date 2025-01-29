package com.example.productadminapp

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txt1: TextView
    lateinit var sharedPreferences: SharedPreferences

    lateinit var f1: FloatingActionButton
    lateinit var listView: ListView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)
        txt1 = findViewById(R.id.txt1)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        f1 = findViewById(R.id.f1)
        listView = findViewById(R.id.list)
        list = ArrayList()
        registerForContextMenu(listView)

        txt1.setText("Welcome : "+sharedPreferences.getString("e1","default username"))


        f1.setOnClickListener {

            startActivity(Intent(applicationContext, AddProductActivity::class.java))
        }


        var stringrequest = StringRequest(Request.Method.GET, "https://prakrutitech.buzz/Kapil/view.php", object : Response.Listener<String>{
            override fun onResponse(response: String?) {

                var jsonArray = JSONArray(response)

                for (i in 0 until jsonArray.length())
                {
                    var jsonObject = jsonArray.getJSONObject(i)

                    var pid = jsonObject.getString("p_id")
                    var pimg = jsonObject.getString("p_image")
                    var pname = jsonObject.getString("p_name")
                    var pprice = jsonObject.getString("p_price")
                    var pstatus = jsonObject.getString("p_status")
                    var pdesc = jsonObject.getString("p_desc")

                    var m = Model()
                    m.pid = pid
                    m.pname = pname
                    m.pprice = pprice
                    m.pstatus = pstatus
                    m.pdesc = pdesc
                    m.pimg = pimg

                    list.add(m)
                }

                var myadapter = MyAdapter(applicationContext, list)
                listView.adapter = myadapter
            }

        },object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)

    }

    //ContextMenu

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        var m1 = menu!!.add(0,1,0,"Update")
        var m2 = menu.add(0,2,0,"Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var acm: AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        var id = acm.position
        var m =list[id]

        when(item.itemId)
        {
            1->
            {
                var i = Intent(applicationContext, Update_P_Activity::class.java)
                i.putExtra("pid",m.pid)
                i.putExtra("pname",m.pname)
                i.putExtra("pprice",m.pprice)
                i.putExtra("pstatus",m.pstatus)
                i.putExtra("pdesc",m.pdesc)
                startActivity(i)
            }

            2->
            {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Are you sure you want to delete ?")
                alert.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->

                    var stringRequest : StringRequest = object : StringRequest(Request.Method.POST, "https://prakrutitech.buzz/Kapil/delete.php",
                        Response.Listener {

                            Toast.makeText(applicationContext,"Deleted",Toast.LENGTH_LONG).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))

                        },

                        Response.ErrorListener {

                            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

                        })

                    {
                        override fun getParams(): MutableMap<String, String>?
                        {
                            var map = HashMap<String,String>()
                            map["p_id"]= m.pid
                            return map
                        }

                    }
                    var queue:RequestQueue = Volley.newRequestQueue(this)
                    queue.add(stringRequest)

                })

                alert.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
                alert.show()
            }
        }
        return super.onContextItemSelected(item)
    }

    // OptionsMenu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.i1 -> {

                sharedPreferences.edit().clear().commit()
                Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            R.id.i2 -> {
                Toast.makeText(applicationContext, "Clicked Contact us", Toast.LENGTH_SHORT).show()
            }

            R.id.i3 -> {
                Toast.makeText(applicationContext, "Clicked About us", Toast.LENGTH_SHORT).show()
            }

        }

        return super.onOptionsItemSelected(item)

    }

}