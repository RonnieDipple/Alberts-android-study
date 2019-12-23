package com.stepasha.shoppingcalculator

import android.view.View


operator fun View.plus(sum: Float): Float{

    return sum

}


operator fun View.times(sum: Double):Double{

    return sum
}
operator fun <T> MutableCollection<in T>.plusAssign(element: T) {
    this.add(element)
}
