package com.stepasha.dependencyinjection

import com.stepasha.dependencyinjection.model.Item
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitNetworkInterface {

    @GET("products.json")
    fun getMakeup(@Query("brand") brand: String): Single<Array<Item>>
}