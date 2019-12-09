package com.stepasha.favoritemoviessprint.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject


@Entity
class FavoriteMovie{
    companion object {
        const val INVALID_ID = 0
    }
    var title:String=""
    var watched:Boolean=false
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    var id:Int=0


    constructor(title:String,watched:Boolean, id:Int){
        this.title=title
        this.watched=watched
        this.id=id
    }

    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonObject.getString("title")
        } catch (e: JSONException) {
            this.title = ""
        }
        try {
            this.watched = jsonObject.getBoolean("has_been_watched")
        } catch (e: JSONException) {
            this.watched = false
        }
        try {
            this.id = jsonObject.getString("id").toInt()
        }catch (e: JSONException) {
            this.id = 0
        }
    }
}