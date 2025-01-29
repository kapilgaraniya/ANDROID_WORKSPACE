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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class LoginActivity : AppCompatActivity() {

    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var edt3: EditText
    lateinit var btn1: Button
    lateinit var txt1:TextView
    lateinit var sharedPreferences: SharedPreferences


    @SuppressLint("MissingInflatedId")
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
        edt3 = findViewById(R.id.edt3)
        btn1 = findViewById(R.id.btn_login)
        txt1 = findViewById(R.id.signup_link)
        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)

        if(sharedPreferences.getBoolean("tops",false) && !sharedPreferences.getString("e1","")!!.isEmpty())
        {
            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        txt1.setOnClickListener {

            startActivity(Intent(applicationContext, SignupActivity::class.java))
        }

        btn1.setOnClickListener {

            var email = edt1.text.toString()
            var password = edt2.text.toString()
            var name = edt3.text.toString()


            var stringrequest : StringRequest = object :StringRequest(

                Request.Method.POST, "https://prakrutitech.buzz/Kapil/login.php", {
                    response ->

                    try {
                        if (response.trim().equals(0)) {
                            Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                        }
                        else {

                            var s1 :SharedPreferences.Editor = sharedPreferences.edit()
                            s1.putBoolean("tops",true)
                            s1.putString("e1",email)
                            s1.putString("n1",name)
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
                    map["n1"]=edt3.text.toString()

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