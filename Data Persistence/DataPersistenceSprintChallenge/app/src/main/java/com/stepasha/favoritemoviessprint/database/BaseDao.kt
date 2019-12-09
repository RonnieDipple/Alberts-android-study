package com.stepasha.favoritemoviessprint.database

import androidx.room.*


@Dao //Must be added so Room knows this is a Dao



interface BaseDao<in T> { //Must contain an interface because that is essential what a dao is

    //Insert, Delete and Update here will be inherited by daos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Insert(t: T): Long // what to do incase of conflict? insert the t is what it does!

    @Delete
    fun delete(type: T) //Delete the movie etc

    @Update
    fun update(type: T) //update the movie etc

}