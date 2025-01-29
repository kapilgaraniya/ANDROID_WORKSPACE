package com.example.productcrudtask

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.GridView
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txt1:TextView
    lateinit var sharedPreferences:SharedPreferences

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

        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)
        txt1 = findViewById(R.id.txt1)
        toolbar = findViewById(R.id.toolbar)
       // toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        //  setSupportActionBar(toolbar.title.remo)

        txt1.setText("Welcome : "+sharedPreferences.getString("n1","default username"))


        var f1 = AllFragment()
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fl1,f1).commit()

    }

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

            R.id.i4 -> {

                val menuView = findViewById<View>(item.itemId)
                showFragmentPopup(menuView)

            }

        }

        return super.onOptionsItemSelected(item)

    }

    private fun showFragmentPopup(anchorView: View) {
        val popupView = LayoutInflater.from(this).inflate(R.layout.btn, null)

        val popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.setBackgroundDrawable(getDrawable(R.drawable.popup_background))

        popupWindow.isOutsideTouchable = true

        val btnLtoH = popupView.findViewById<Button>(R.id.btnLtoH)
        val btnHtoL = popupView.findViewById<Button>(R.id.btnHtoL)
        val btnAll = popupView.findViewById<Button>(R.id.all)


        btnLtoH.setOnClickListener {

            Toast.makeText(applicationContext, "Change Phones According Low to High Price", Toast.LENGTH_SHORT).show()

            val fragment = LtoHFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl1, fragment)
                .commit()
            popupWindow.dismiss()
        }

        btnHtoL.setOnClickListener {

            Toast.makeText(applicationContext, "Change Phones According High to Low Price", Toast.LENGTH_SHORT).show()

            val fragment = HtoLFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl1, fragment)
                .commit()
            popupWindow.dismiss()
        }

        btnAll.setOnClickListener {

            Toast.makeText(applicationContext, "Change Phones According Default Price", Toast.LENGTH_SHORT).show()

            val fragment = AllFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl1, fragment)
                .commit()
            popupWindow.dismiss()
        }

        popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.END)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        finishAffinity()
    }

}