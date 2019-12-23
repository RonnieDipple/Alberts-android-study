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
            myShoppingList.plusAssign(ShoppingItem(myItem, myPrice))

            textviewMyList.text = myShoppingList.toString()
            var sum = 0.0

          for (i in myShoppingList) {
              sum += i.price!!.toDouble()
              textviewItemTotal.text = sum.toString()
          }
          val taxRate = sum
            when {
                checkbox1.isChecked -> {
                    val denverRate = 0.075
                    val denverRateTotal= taxRate * denverRate + sum
                    textviewTotalInNumber.text = denverRateTotal.toString()
                }
                checkbox2.isChecked -> {
                    val auroraRate = 0.02
                    val auroraRateTotal = taxRate * auroraRate + sum
                    textviewTotalInNumber.text = auroraRateTotal.toString()
                }
                else -> {
                    textviewTotalInNumber.text = sum.toString()
                }
            }






        }



    }


}

