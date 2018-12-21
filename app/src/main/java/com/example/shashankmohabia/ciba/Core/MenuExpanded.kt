package com.example.shashankmohabia.ciba.Core

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.R
import org.w3c.dom.Text

class MenuExpanded : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_expanded)
        val itemName = findViewById<TextView>(R.id.expanded_menu_item_name)
        val itemPrice = findViewById<TextView>(R.id.expanded_menu_item_price)
        val btnAdd =findViewById<ImageView>(R.id.btn_add_item)
        val btnRemove =  findViewById<ImageView>(R.id.btn_remove_item)
        val counter = findViewById<TextView>(R.id.counter_item)
        var count :Int = 0

        itemName.text = intent.getStringExtra("itemName")
        itemPrice.text = intent.getStringExtra("itemPrice")
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
