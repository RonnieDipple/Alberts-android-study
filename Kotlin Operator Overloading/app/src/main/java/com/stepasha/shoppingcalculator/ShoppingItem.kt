package com.stepasha.shoppingcalculator

data class ShoppingItem(var shoppingItem: String?, var price: Double?){
    init {
        this.shoppingItem = shoppingItem
        this.price = price

    }



    override fun toString(): String {



        return  "$shoppingItem" +
                "$price\n"
    }

}





