package com.example.shashankmohabia.ciba.Utils.Extensions

class CartData {
    var id : String? = null
    var name : String? =null
    var rate: Int =0
    var qty :Int = 0
    var amt : Int = 0
constructor(){

}
    constructor(id: String?, name: String?, rate: Int, qty: Int, amt: Int) {
        this.id = id
        this.name = name
        this.rate = rate
        this.qty = qty
        this.amt = amt
    }
}

object data{val items = mutableListOf<CartData>(
                CartData("NA","NA",0,0,0)
)

}
