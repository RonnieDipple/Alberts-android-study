package com.stepasha.favoritemoviessprint.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stepasha.favoritemoviessprint.model.FavoriteMovie


@Database(entities = [FavoriteMovie::class], version = 3, exportSchema = false)

abstract class FavouriteMovieDB : RoomDatabase() {

    abstract fun favouriteMovieDao(): FavoriteMovieDao
}