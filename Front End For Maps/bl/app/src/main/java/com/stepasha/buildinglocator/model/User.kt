package com.stepasha.buildinglocator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "id") @SerializedName("userId")
    var id: Int,

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_roomId")
    var userRoomId: Int? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("firstName")
    val firstName: String? = null,

    @SerializedName("lastName")
    val lastName: String? = null,

    @SerializedName("password")
    val password: String? = null,

    @ColumnInfo(name = "user_group_id")
    @SerializedName("user_group_id")
    val userGroupId: Int? = null
) : Serializable