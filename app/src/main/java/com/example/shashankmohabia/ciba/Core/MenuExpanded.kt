package com.example.shashankmohabia.ciba.Core

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.R

class MenuExpanded : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_expanded)
        val price : Int = intent.getIntExtra("itemPrice",0)
        val isVeg : Boolean = intent.getBooleanExtra("isVeg",true)
        val item_image =findViewById<ImageView>(R.id.item_image)
        val itemName = findViewById<TextView>(R.id.expanded_menu_item_name)
        val itemPrice = findViewById<TextView>(R.id.expanded_menu_item_price)
        val prepTime = findViewById<TextView>(R.id.preptime)
        val availability = findViewById<TextView>(R.id.availability_item)
        val icon = findViewById<ImageView>(R.id.icon)
        val btnAdd =findViewById<ImageView>(R.id.btn_add_item)
        val btnRemove =  findViewById<ImageView>(R.id.btn_remove_item)
        val counter = findViewById<TextView>(R.id.counter_item)
        var count :Int = 0

        itemName.text = intent.getStringExtra("itemName")
        itemPrice.text = price.toString()
        prepTime.text = intent.getStringExtra("prepTime")
        availability.text = intent.getStringExtra("availability")
        if(isVeg.equals(true)){
            Toast.makeText(this,"VEG",Toast.LENGTH_LONG).show()

            icon.setImageResource(R.drawable.veg_food_symbol)
        }//else{
           // Toast.makeText(this,"NONVEG",Toast.LENGTH_LONG).show()
            //icon.setImageResource(R.drawable.non_veg_food_symbol)
        //}
        btnAdd.setOnClickListener {
            count++
            counter.text=count.toString()

        }
        btnRemove.setOnClickListener {
            if(count>0){count --
                counter.text=count.toString()
            }
        }
       // Toast.makeText(this,itemName,Toast.LENGTH_LONG).show()

    }
}
