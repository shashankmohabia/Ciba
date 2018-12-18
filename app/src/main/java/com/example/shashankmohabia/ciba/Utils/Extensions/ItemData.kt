package com.example.shashankmohabia.ciba.Utils.Extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.shashankmohabia.ciba.R
import kotlinx.android.synthetic.main.menu_item.*

class ItemData{
     var item_name : String
     var item_price :String
    var priority : Int = 0
init {
    //empty consructor needed
}
    constructor(item_name: String, item_price: String, priority: Int) {
        this.item_name = item_name
        this.item_price = item_price
        this.priority = priority
    }
    fun getItemName(): String {
        return item_name
    }
 fun getItemPrice():String{
        return item_price
    }
    fun getItemPriority():Int{
        return priority
    }

}