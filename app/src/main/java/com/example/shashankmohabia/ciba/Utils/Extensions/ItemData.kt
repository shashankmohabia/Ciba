package com.example.shashankmohabia.ciba.Utils.Extensions

class ItemData{
    var availableOrNot : String?=null
    var vegOrNot : Boolean? = null
    var name : String? =null
    var preptime : String? =null
    var price :Int = 0

    constructor(){

        //empty constructor needed
    }

    constructor(availableOrNot: String?, vegOrNot: Boolean?, name: String?, preptime: String?, price: Int) {
        this.availableOrNot = availableOrNot
        this.vegOrNot = vegOrNot
        this.name = name
        this.preptime = preptime
        this.price = price
    }


}