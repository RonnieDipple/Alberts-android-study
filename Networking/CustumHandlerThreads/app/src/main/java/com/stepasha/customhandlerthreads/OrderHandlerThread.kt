package com.stepasha.customhandlerthreads

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import java.util.*
import kotlin.system.exitProcess

//todo 1 after creating Runnable create own handler thread
//ui handler handles the UI stuff
class OrderHandlerThread(private var uiHandler: MainActivity.UiHandler): HandlerThread("OrderHandlerThread"){

    //declare handler and random
    private var handler: Handler? = null
    private val random = Random()


    private fun convertCurrency(foodPriceInDollars: Float): Float {
        return foodPriceInDollars * 68.45f
    }
    //additional items on the side
    private fun attachSideOrder(): String {
        val randomOrder = random.nextInt(3)
        return when (randomOrder) {
            0 -> "Chips"
            1 -> "Salad"
            else -> "Nachos"
        }
    }
    //
    private fun getHandler(looper: Looper):Handler{
        return object: Handler(looper){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                //create a food order
                val foodOrder = msg?.obj as FoodOrder
                //convert the order total to us dollars
                foodOrder.foodPrice = convertCurrency(foodOrder.foodPrice)
                //attach that random side order
                foodOrder.sideOrder = attachSideOrder()
                //then procces the message
                val processMessage = Message()
                processMessage.obj = foodOrder
                //send the message to UI once proccessed
                uiHandler.sendMessage(processMessage)




            }
        }
    }
//todo 2 set up the handler you just created above
    override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = getHandler(looper)
    }
    fun sendOrder(foodOrder: FoodOrder){
        val message = Message()
        message.obj = foodOrder
        handler?.sendMessage(message)
    }
}
