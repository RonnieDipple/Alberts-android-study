package com.stepasha.shoppingcalculator

data class ShoppingItem(var shoppingItem: String?, var price: Double?){
    init {
        this.shoppingItem = shoppingItem
        this.price = price
    }
    operator fun plus(other: Double): Double=
        0.0 + 0.0
}



