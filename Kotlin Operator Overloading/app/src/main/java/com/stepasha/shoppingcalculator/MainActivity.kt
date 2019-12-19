package com.stepasha.shoppingcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myShoppingList = mutableListOf<ShoppingItem>()

        val myItem = edittextItemName.text.toString()
        val myPrice = edittextItemPrice.text.toString()
        val myItemPrice = "$myItem and $myPrice"

        buttonAdd.setOnClickListener {
            val myItem = edittextItemName.text.toString()
            val myPrice = edittextItemPrice.text.toString().toDouble()
            val myItemPrice = "$myItem and $myPrice"
            myShoppingList.add(ShoppingItem(myItem, myPrice))
            textviewMyList.text = "$myShoppingList"
            var sum = 0.0
            for (i in myShoppingList) {
                sum += i.price!!.toDouble()
                textviewItemTotal.text = sum.toString()
            }




        }



    }
}
