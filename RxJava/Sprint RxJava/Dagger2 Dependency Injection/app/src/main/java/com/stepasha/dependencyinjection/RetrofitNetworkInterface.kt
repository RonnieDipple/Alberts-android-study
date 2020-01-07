package com.stepasha.dependencyinjection

import androidx.core.content.contentValuesOf
import com.stepasha.dependencyinjection.model.Item
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleContains

import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitNetworkInterface {

    @GET("products.json")

    fun getMakeup(@Query("brand") brand: String): Observable<Array<Item>>
}