package com.example.shashankmohabia.ciba

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.android.synthetic.main.bootsplashscreen.*
import java.lang.Thread.sleep

class BootSplashScreen : AppCompatActivity(){


    override fun onCreate(savedInstancestate: Bundle?){
        super.onCreate(savedInstancestate)
        setContentView(R.layout.bootsplashscreen)
        val lo:ImageView=findViewById<ImageView>(R.id.logo)
        lo.setOnClickListener {
        val intent= Intent(this,Info::class.java)

        startActivity(intent)
        finish()}

    }
}