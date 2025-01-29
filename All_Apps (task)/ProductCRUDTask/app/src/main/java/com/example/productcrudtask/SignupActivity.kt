package com.example.productcrudtask

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignupActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn:Button
    lateinit var link:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)
        edt4 = findViewById(R.id.edt4)
        btn = findViewById(R.id.btn_signup)
        link = findViewById(R.id.login_link)

        link.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        btn.setOnClickListener {

            var n = edt1.text.toString()
            var u = edt2.text.toString()
            var e = edt3.text.toString()
            var p = edt4.text.toString()

            var stringrequest = object : StringRequest(Request.Method.POST,"https://prakrutitech.buzz/CRUD/insert.php",
                Response.Listener {

                    Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, LoginActivity::class.java))

                },
                Response.ErrorListener {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

                })
            {
                override fun getParams(): MutableMap<String, String>? {

                    var map = HashMap<String, String>()

                    map["name"] = n
                    map["username"] = u
                    map["email"] = e
                    map["password"] = p

                    return map
                }
            }

            var queue:RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringrequest)

        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        var alert = AlertDialog.Builder(this)
        finish()
        alert.show()
    }
}