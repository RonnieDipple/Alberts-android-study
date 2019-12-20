package com.stepasha.cryptofirstrx

import android.util.Log
import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Observable

class MyRepository(private val api: API) {
    //TODO 3: Create a function to call API and return an Observable
    fun getCryptoData(currencies: String): Observable<LinkedTreeMap<Any, Any>> {
        return api.getCryptoData(currencies)
            .doOnNext {
                Log.d("getCryptoData", "Dispatching ${it.size} crypto data from API...")
            }
    }
}
