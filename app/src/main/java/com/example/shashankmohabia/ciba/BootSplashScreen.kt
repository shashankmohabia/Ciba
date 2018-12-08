package com.example.shashankmohabia.ciba

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.lang.Thread.sleep

class BootSplashScreen : AppCompatActivity(){


    override fun onCreate(savedInstancestate: Bundle?){
        super.onCreate(savedInstancestate)
        setContentView(R.layout.bootsplashscreen)

        val intent= Intent(this,Info::class.java)
       Thread{
         try{
            sleep(1500)
            }finally{
                            startActivity(intent)
                             finish()
                    }
       }.start()


    }
}