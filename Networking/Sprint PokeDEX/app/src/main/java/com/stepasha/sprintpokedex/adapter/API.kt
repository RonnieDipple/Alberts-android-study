package com.stepasha.sprintpokedex.adapter

import com.stepasha.sprintpokedex.model.Pokemon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface API {

    @GET("pokemon/")
    fun getPokemon(): Call<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    companion object Factory {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): API {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            logger.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(API::class.java)
        }
    }
}