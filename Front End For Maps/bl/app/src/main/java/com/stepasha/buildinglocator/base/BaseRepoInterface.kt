package com.stepasha.buildinglocator.base

import com.stepasha.buildinglocator.retrofit.LoginServiceSql
import com.stepasha.buildinglocator.retrofit.ServiceBuilder


interface BaseRepoInterface<T> {

    fun apiFactory(): LoginServiceSql {
        val receiptTrackerFactory by lazy {
            ServiceBuilder.create()
        }
        return receiptTrackerFactory
    }

    fun create(obj: T)

    fun update(obj: T)

    fun delete(obj: T)
}