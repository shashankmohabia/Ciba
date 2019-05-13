package com.example.shashankmohabia.ciba.Auth

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.ciba.R
import kotlinx.android.synthetic.main.merchant_options_layout.*

class SelectMerchant:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.merchant_options_layout)
        setSupportActionBar(toolbar_select_merchant)
    }

}