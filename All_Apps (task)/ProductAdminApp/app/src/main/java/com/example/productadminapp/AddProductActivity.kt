package com.example.productadminapp

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.gotev.uploadservice.MultipartUploadRequest


class AddProductActivity : AppCompatActivity() {

    lateinit var btn1: Button
    lateinit var img: ImageView
    lateinit var edt1: EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn2:Button
    lateinit var filepath: Uri
    lateinit var bitmap: Bitmap

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn1 = findViewById(R.id.c_img_btn)
        img = findViewById(R.id.img)
        edt1 = findViewById(R.id.pname)
        edt2 = findViewById(R.id.pprice)
        edt3 = findViewById(R.id.pststus)
        edt4 = findViewById(R.id.pdesc)
        btn2 = findViewById(R.id.btn1)

        permissions()

        btn1.setOnClickListener {

            var i = Intent()
            i.type = "image/*"
            i.action =Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"Select Picture"),1)
        }

        btn2.setOnClickListener {

            var p_name = edt1.getText().toString()
            var p_price = edt2.getText().toString()
            var p_status = edt3.getText().toString()
            var p_desc = edt4.getText().toString()
            var path = getPath(filepath)

            MultipartUploadRequest(this, "https://prakrutitech.buzz/Kapil/insert.php")
                .addFileToUpload(path, "p_image")
                .addParameter("p_name", p_name)
                .addParameter("p_price", p_price)
                .addParameter("p_status", p_status)
                .addParameter("p_desc", p_desc)
                .setMaxRetries(2)
                .startUpload()

            Handler().postDelayed(Runnable {

                var i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)

            },99)

            Toast.makeText(applicationContext, "Product Added Successfully", Toast.LENGTH_SHORT).show()

        }
    }

    private fun getPath(uri: Uri): String? {

        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1) {
                return it.getString(columnIndex)
            }
        }
        return null

    }

    private fun permissions() {

        if(checkSelfPermission(READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),100)
        }
        else
        {
            Toast.makeText(applicationContext,"Permission alread granted",Toast.LENGTH_LONG).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            filepath = data.data!!
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            img.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}