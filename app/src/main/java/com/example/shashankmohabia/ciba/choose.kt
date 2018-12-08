package com.example.shashankmohabia.ciba

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.choose.*

class choose:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose)

        merchantbutton.setOnClickListener {
            val merchantintent= Intent(this,Login_merchant::class.java)
            startActivity(merchantintent)
            finish()
        }
        customerbutton.setOnClickListener {
            val customerintent= Intent(this,Login_customer::class.java)
            startActivity(customerintent)
            finish()
        }
    }



}