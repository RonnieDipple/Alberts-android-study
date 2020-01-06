package com.stepasha.buildinglocator.retrofit

import com.stepasha.buildinglocator.model.*
import com.stepasha.buildinglocator.model.Map
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginServiceSql{
    @POST("map/map")
  //  fun createProperty(@Header("Authorization") authToken: String, @Body newProperty: NewProperty): Call<Void>
    fun createProperty(@Body newProperty: NewProperty) : Call<Void>

    @GET("map/maps")
    //fun getAllProperties(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllMaps() : Call<MutableList<Map>>


    @POST("createnewuser")
    fun createUser(@Body newUser: NewUser): Call<RegisterResponse>

    @POST("login")
    fun userLoginPost(@Body userLogin: UserLogin) : Call<LoginResponse>

    companion object {

        const val BASE_URL = "https://building-locator.herokuapp.com/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

}