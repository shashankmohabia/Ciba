package com.example.shashankmohabia.ciba.Utils.Extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.shashankmohabia.ciba.R
import kotlinx.android.synthetic.main.menu_item.*

class ItemData{
     var name : String? =null
     var price :String? = null

    constructor(name: String?, price: String?) {
        this.name = name
        this.price = price
    }

    init {
    //empty consructor needed
}


}