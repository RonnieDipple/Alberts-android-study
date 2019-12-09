package com.stepasha.favoritemoviessprint.database

import androidx.lifecycle.LiveData
import androidx.room.*

import com.stepasha.favoritemoviessprint.model.FavoriteMovie


@Dao



interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createFavouriteMovie(favouriteMovie: FavoriteMovie)

    @Query("SELECT * FROM FavoriteMovie")
    fun readAllFavouriteMovies(): LiveData<List<FavoriteMovie>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavouriteMovie(favouriteMovie:FavoriteMovie)


    @Delete
    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie)
}