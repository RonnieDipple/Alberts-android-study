package com.stepasha.buildinglocator.room

import androidx.lifecycle.LiveData
import com.stepasha.buildinglocator.base.BaseRepoInterface
import com.stepasha.buildinglocator.model.User
import com.stepasha.buildinglocator.model.UserLogin

interface UserRepoInterface : BaseRepoInterface<User> {

    fun getUserData(id: Int): LiveData<User>

    fun loginUser(user: UserLogin)

    fun nukeUserTable()

    fun deleteOldUsers()
}