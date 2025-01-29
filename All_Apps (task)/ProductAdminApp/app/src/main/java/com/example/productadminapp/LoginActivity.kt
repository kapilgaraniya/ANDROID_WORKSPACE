package com.example.productadminapp

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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class LoginActivity : AppCompatActivity() {

    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var btn1: Button
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn_login)
        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)

        if(sharedPreferences.getBoolean("tops",false) && !sharedPreferences.getString("e1","")!!.isEmpty())
        {
            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        }


        btn1.setOnClickListener {

            var email = edt1.text.toString()
            var password = edt2.text.toString()


            var stringrequest : StringRequest = object :StringRequest(

                Request.Method.POST, "https://prakrutitech.buzz/Kapil/admininsert.php", {
                    response ->

                    try {
                        if (response.trim().equals(0)) {
                            Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                        }
                        else {

                            var s1 :SharedPreferences.Editor = sharedPreferences.edit()
                            s1.putBoolean("tops",true)
                            s1.putString("e1",email)
                            s1.putString("p1",password)
                            s1.commit()

                            Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }
                    }

                    catch (e: JSONException)
                    {
                        print(e)
                    }

                },
                {
                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                })

            {
                override fun getParams(): MutableMap<String, String>?
                {
                    var map = HashMap<String,String>()
                    map["e1"]=edt1.text.toString()
                    map["p1"]=edt2.text.toString()

                    return map
                }
            }

            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringrequest)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        var alert = AlertDialog.Builder(this)
        finishAffinity()
        alert.show()
    }
}