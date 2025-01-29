package com.example.amul_app_task

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn:Button



    lateinit var t1:TextView
    lateinit var im1:ImageView

    lateinit var t2:TextView
    lateinit var im2:ImageView

    lateinit var t3:TextView
    lateinit var im3:ImageView

    lateinit var t4:TextView
    lateinit var im4:ImageView

    lateinit var t5:TextView
    lateinit var im5:ImageView

    lateinit var t6:TextView
    lateinit var im6:ImageView

    lateinit var t7:TextView
    lateinit var im7:ImageView

    lateinit var t8:TextView
    lateinit var im8:ImageView

    lateinit var t9:TextView
    lateinit var im9:ImageView

    lateinit var t10:TextView
    lateinit var im10:ImageView



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

        btn = findViewById(R.id.btn)

        btn.setOnClickListener {

            var alert = AlertDialog.Builder(this)
            var layout = LayoutInflater.from(applicationContext)
            var View = layout.inflate(R.layout.disign, null)

            var edt1:EditText = View.findViewById(R.id.edt1)
            var btn1:Button = View.findViewById(R.id.btn1)


            btn1.setOnClickListener {

                var data = edt1.text.toString()

                Toast.makeText(applicationContext, ""+data, Toast.LENGTH_SHORT).show()
            }

            alert.setView(View)
            alert.show()


        }


        im1 = findViewById(R.id.img1)
        im2 = findViewById(R.id.img2)
        im3 = findViewById(R.id.img3)
        im4 = findViewById(R.id.img4)
        im5 = findViewById(R.id.img5)
        im6 = findViewById(R.id.img6)
        im7 = findViewById(R.id.img7)
        im8 = findViewById(R.id.img8)
        im9 = findViewById(R.id.img9)
        im10 = findViewById(R.id.img10)


        im1.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Atta", Toast.LENGTH_SHORT).show()
        }



        im2.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Chocolate", Toast.LENGTH_SHORT).show()
        }


        im3.setOnClickListener {
            Toast.makeText(applicationContext, "This is Amul Ghee", Toast.LENGTH_SHORT).show()
        }

        im4.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Butter", Toast.LENGTH_SHORT).show()
                }

        im5.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Hony", Toast.LENGTH_SHORT).show()
                }

        im6.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Lassi", Toast.LENGTH_SHORT).show()
                }

        im7.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Milk", Toast.LENGTH_SHORT).show()
                }

        im8.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Potato", Toast.LENGTH_SHORT).show()
                }

        im9.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Peda", Toast.LENGTH_SHORT).show()
                }

        im10.setOnClickListener {
                    Toast.makeText(applicationContext, "This is Amul Ladoo", Toast.LENGTH_SHORT).show()
                }




        t1 = findViewById(R.id.atta)
        t2 = findViewById(R.id.chocolate)
        t3 = findViewById(R.id.ghee)
        t4 = findViewById(R.id.butter)
        t5 = findViewById(R.id.honey)
        t6 = findViewById(R.id.lassi)
        t7 = findViewById(R.id.milk)
        t8 = findViewById(R.id.potato)
        t9 = findViewById(R.id.peda)
        t10 = findViewById(R.id.ladoo)


        t1.setOnClickListener{
            var a = Intent(applicationContext,Atta::class.java)
            startActivity(a)
        }


        t2.setOnClickListener{
            var b = Intent(applicationContext,chocolate::class.java)
            startActivity(b)
        }


        t3.setOnClickListener{
            var c = Intent(applicationContext,Ghee::class.java)
            startActivity(c)
        }

        t4.setOnClickListener{
            var c = Intent(applicationContext,butter::class.java)
            startActivity(c)
        }


        t5.setOnClickListener{
            var c = Intent(applicationContext,honey::class.java)
            startActivity(c)
        }


        t6.setOnClickListener{
            var c = Intent(applicationContext,lassi::class.java)
            startActivity(c)
        }


        t7.setOnClickListener{
            var c = Intent(applicationContext,milk::class.java)
            startActivity(c)
        }


        t8.setOnClickListener{
            var c = Intent(applicationContext,potato::class.java)
            startActivity(c)
        }


        t9.setOnClickListener{
            var c = Intent(applicationContext,peda::class.java)
            startActivity(c)
        }


        t10.setOnClickListener{
            var c = Intent(applicationContext,ladoo::class.java)
            startActivity(c)
        }


    }

}