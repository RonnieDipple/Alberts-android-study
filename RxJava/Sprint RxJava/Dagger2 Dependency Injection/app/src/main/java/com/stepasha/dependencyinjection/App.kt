package com.stepasha.dependencyinjection

import android.app.Application
import com.stepasha.dependencyinjection.dagger.DaggerNetworkComponent
import com.stepasha.dependencyinjection.dagger.NetworkComponent
import com.stepasha.dependencyinjection.dagger.NetworkModule
import java.net.URL


class App : Application() {

    val appComponent by lazy {

        DaggerNetworkComponent
            .builder()
            .build()

    }

}