package com.stepasha.dessertlifecycles

import android.app.Application
import timber.log.Timber

class PusherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //initializing timber
        Timber.plant(Timber.DebugTree())
    }
}