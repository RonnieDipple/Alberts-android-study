package com.stepasha.retrofitgithubquery

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Retro {

    private val service: GithubService

    companion object {
        const val BASE_URL = "https://api.github.com/"


    }
        init {



            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            logger.level = HttpLoggingInterceptor.Level.BODY
            val gson = GsonBuilder()
                .setLenient()
                .create()


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            service = retrofit.create(GithubService::class.java)



        }
    fun getRepositories(callback: Callback<RepoResult>){
       service.searchRepo().enqueue(callback)
    }

}



