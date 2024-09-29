package com.example.milkonmobile

import android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.READ_MEDIA_AUDIO
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView

class MainActivity : AppCompatActivity() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    lateinit var sliderLayout:SliderLayout

    lateinit var btn1:Button
    lateinit var btn2:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sliderLayout = findViewById(R.id.slider)
        var map = HashMap<String,Int>()

        map.put("a",R.drawable.cowghee)
        map.put("b",R.drawable.taaza)
        map.put("c",R.drawable.gold_detail)
        map.put("d",R.drawable.kesar_peda)

        for (i in map.keys)
        {
            var textSlider = TextSliderView(this)
            textSlider.image(map.get(i)!!)

            sliderLayout.addSlider(textSlider)
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipPage)
//--------------------------------------------

        listView = findViewById(R.id.list)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        list = ArrayList()

        list.add(Model("milk", R.drawable.taaza,"Rs.  35.0"))
        list.add(Model("ghee", R.drawable.cowghee,"Rs.  35.0"))
        list.add(Model("peda", R.drawable.kesar_peda,"Rs.  35.0"))
        list.add(Model("milk", R.drawable.gold_detail,"Rs.  35.0"))
        list.add(Model("milk", R.drawable.taaza,"Rs.  35.0"))
        list.add(Model("ghee", R.drawable.cowghee,"Rs.  35.0"))
        list.add(Model("peda", R.drawable.kesar_peda,"Rs.  35.0"))
        list.add(Model("milk", R.drawable.gold_detail,"Rs.  35.0"))
        list.add(Model("milk", R.drawable.taaza,"Rs.  35.0"))
        list.add(Model("peda", R.drawable.kesar_peda,"Rs.  35.0"))


        var adapter = MyAdapter(applicationContext, list)
        listView.adapter = adapter

        //-------------------------------

        btn1 = findViewById(R.id.btndate)
        btn2 = findViewById(R.id.btntime)
        registerForContextMenu(btn1)

        btn1.setOnClickListener {

            var d = DateFragment()
            d.show(supportFragmentManager,"")

        }

        btn2.setOnClickListener {

            var t = TimeFragment()
            t.show(supportFragmentManager,"")

        }

        //-----------------------------

        checkPermission()

        if (checkPermission())
        {
            Toast.makeText(applicationContext, "permission granted", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(applicationContext, "please allow the permission", Toast.LENGTH_SHORT).show()
            requestpermission()
        }

    }

    private fun requestpermission()
    {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(CALL_PHONE, READ_MEDIA_AUDIO, ACCESS_FINE_LOCATION),200)
    }

    private fun checkPermission():Boolean
    {
        val a = ContextCompat.checkSelfPermission(this@MainActivity, CALL_PHONE)
        val b = ContextCompat.checkSelfPermission(this@MainActivity, READ_MEDIA_AUDIO)
        val c = ContextCompat.checkSelfPermission(this@MainActivity, ACCESS_BACKGROUND_LOCATION)

        return a == PackageManager.PERMISSION_GRANTED && b == PackageManager.PERMISSION_GRANTED && c == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.i3 ->{
                Toast.makeText(applicationContext, "Clicked Contact us", Toast.LENGTH_SHORT).show()
            }

            R.id.i4 ->
            {
                Toast.makeText(applicationContext, "Clicked About us", Toast.LENGTH_SHORT).show()
            }

            R.id.viewmore->
            {
                var i = Intent(applicationContext, MainActivity2::class.java)
                startActivity(i)
            }

        }

        return super.onOptionsItemSelected(item)
    }

    //------------


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {

        menuInflater.inflate(R.menu.contextmenu,menu)

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var acm: AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo

        when(item.itemId)
        {
            R.id.i1 ->
            {
                Toast.makeText(applicationContext, ""+acm, Toast.LENGTH_SHORT).show()
            }
        }

        return super.onContextItemSelected(item)

        //---------------------------------------

    }

}