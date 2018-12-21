package com.example.shashankmohabia.ciba.Utils.Extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.shashankmohabia.ciba.R
import kotlinx.android.synthetic.main.menu_item.*

class ItemData{
    var availability : String?=null
    var isVeg : Boolean? = null
    var name : String? =null
    var preptime : String? =null
    var price :Int = 0

    constructor(){

        //empty constructor needed
    }

    constructor(availability: String?, isVeg: Boolean?, name: String?, preptime: String?, price: Int) {
        this.availability = availability
        this.isVeg = isVeg
        this.name = name
        this.preptime = preptime
        this.price = price
    }


}