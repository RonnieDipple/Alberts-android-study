package com.stepasha.cryptofirstrx.viewmodel

import android.util.Log
import com.google.gson.internal.LinkedTreeMap
import com.stepasha.cryptofirstrx.MyRepository
import com.stepasha.cryptofirstrx.model.CryptoData

import com.stepasha.cryptofirstrx.model.Price
import io.reactivex.Observable

class DataModelViewModel(private val repo: MyRepository) {

    //TODO 4: Implement Function to Pass Data to ViewModel
    fun getCryptoData(currencies: String): Observable<List<CryptoData>> {
        return repo.getCryptoData(currencies)
            // 1 place the results
            .map {
                handleResult(it)
            }
            // 2 or show the error
            .onErrorReturn {
                Log.d("getCryptoData", "An error occurred")
                arrayListOf<CryptoData>().toList()
            }
    }


    private fun handleResult(result: LinkedTreeMap<Any, Any>): List<CryptoData> {
        val cryptoData = ArrayList<CryptoData>()
        for (entry in result.entries) {
            val cryptoTitle = entry.key as String
            val priceMap = entry.value as LinkedTreeMap<String, Float>
            val prices = ArrayList<Price>()
            for (price in priceMap.entries) {
                val newPrice = Price(price.key, price.value)
                prices.add(newPrice)
            }

            val newCryptoData = CryptoData(cryptoTitle, prices.toList())
            cryptoData.add(newCryptoData)
        }

        return cryptoData
    }

}