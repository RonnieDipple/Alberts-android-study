package com.stepasha.cryptofirstrx

import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val APIKEY = "APIKEY" // TODO 1: Add Your Register API Key Here
const val BASEURL = "https://min-api.cryptocompare.com/"

interface API {
    @Headers("Authorization: $APIKEY")
    @GET("data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR")
    //TODO 2: Declare the function to return an Observable
    fun getCryptoData(@Query("tsyms") currencies: String): Observable<LinkedTreeMap<Any, Any>>

}