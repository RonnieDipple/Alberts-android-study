package com.stepasha.shoppingcalculator

import android.view.View


operator fun View.plus(items: ShoppingItem):String{

    return items.toString()

}

operator fun View.minus(items: MutableList<ShoppingItem>):String{


    return items.toString()

}

operator fun View.times(sum: Double):Double{

    return sum
}