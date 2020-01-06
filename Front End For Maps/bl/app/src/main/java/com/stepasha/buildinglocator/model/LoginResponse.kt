package com.stepasha.buildinglocator.model

data class LoginResponse(val error: Boolean, val message: String, val userId: Int, val token: String)