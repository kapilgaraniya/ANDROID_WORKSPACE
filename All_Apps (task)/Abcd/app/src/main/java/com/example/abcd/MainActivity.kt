package com.example.abcd

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.abcd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tA.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.a)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "A Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tB.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.b)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "B Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tC.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.c)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "C Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tD.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.d)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "D Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tE.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.e)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "E Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tF.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.f)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "F Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tG.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.g)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "G Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tH.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.h)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "H Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tI.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.i)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "I Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tJ.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.j)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "J Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tK.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.k)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "K Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tL.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.l)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "L Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tM.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.m)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "M Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tN.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.n)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "N Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tO.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.o)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "O Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tP.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.p)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "P Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tQ.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.q)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "Q Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tR.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.r)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "R Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tS.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.s)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "S Played ", Toast.LENGTH_SHORT).show()



        }
        binding.tT.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.t)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "T Played ", Toast.LENGTH_SHORT).show()



        }
        binding.tU.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.u)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "U Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tV.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.v)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "V Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tW.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.w)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "W Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tX.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.x)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "X Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tY.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.y)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "Y Played ", Toast.LENGTH_SHORT).show()



        }

        binding.tZ.setOnClickListener{

            var mediaPlayer=MediaPlayer.create(applicationContext,R.raw.z)

            mediaPlayer.start()

            Toast.makeText(applicationContext, "Z Played ", Toast.LENGTH_SHORT).show()



        }

    }
}