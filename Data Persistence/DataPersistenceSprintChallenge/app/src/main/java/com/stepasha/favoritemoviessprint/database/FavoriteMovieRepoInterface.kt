package com.stepasha.favoritemoviessprint.database

import androidx.lifecycle.LiveData
import com.stepasha.favoritemoviessprint.model.FavoriteMovie

interface FavoriteMovieRepoInterface {

    fun createFavouriteMovie(favouriteMovie: FavoriteMovie)

    fun readAllFavouriteMovies(): LiveData<List<FavoriteMovie>>

    fun updateFavouriteMovie(favouriteMovie: FavoriteMovie)

    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie)

}