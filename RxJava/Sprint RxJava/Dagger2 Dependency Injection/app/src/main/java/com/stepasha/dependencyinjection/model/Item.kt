package com.stepasha.dependencyinjection.model

import android.bluetooth.BluetoothGattDescriptor

data class Item(
    val id: Int,
    val brand: String,
    val name: String,
    val description: String,
    val image_link: String,
    val product_colors : List<ProductColors>
)
data class ProductColors(
    val hex_value: String,
    val colour_name: String
){
    override fun toString(): String {
        return "$hex_value : $colour_name"

    }
}
