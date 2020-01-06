package com.stepasha.buildinglocator.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserLogin (

    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

) : Serializable