package com.stepasha.cryptofirstrx

import android.app.Application
import com.stepasha.cryptofirstrx.viewmodel.DataModelViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var cryptoDataApi: API
        private lateinit var cryptoDataMyRepository: MyRepository
        private lateinit var cryptoDataViewModel: DataModelViewModel

        fun injectCryptoDataViewModel() = cryptoDataViewModel
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        cryptoDataApi = retrofit.create(API::class.java)

        cryptoDataMyRepository =
            MyRepository(cryptoDataApi)
        cryptoDataViewModel = DataModelViewModel(cryptoDataMyRepository)
    }
}