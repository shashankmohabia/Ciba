package com.example.shashankmohabia.ciba.Utils.Extensions

public class item_data{
     private lateinit var item_name : String
    private lateinit var item_price : String
  private var priority : Int = 0

    constructor(){
        //neeeded
    }

    constructor(item_name: String, item_price: String, priority: Int) {
        this.item_name = item_name
        this.item_price = item_price
        this.priority = priority
    }

}