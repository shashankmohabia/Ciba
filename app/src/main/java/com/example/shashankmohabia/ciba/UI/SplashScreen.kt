package com.example.shashankmohabia.ciba.UI

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.shashankmohabia.ciba.R
import com.example.shashankmohabia.ciba.Utils.Extensions.data


class SplashScreen : AppCompatActivity(){


    override fun onCreate(savedInstancestate: Bundle?){
        super.onCreate(savedInstancestate)
        setContentView(R.layout.splashscreen)
        val lo:ImageView=findViewById<ImageView>(R.id.logo)
        lo.setOnClickListener {
        val intent= Intent(this, Info::class.java)

        startActivity(intent)
        finish()}

    }

    override fun onStart() {
        super.onStart()
    data.items.clear()
       // searchData.searchItems.clear()
    }
}