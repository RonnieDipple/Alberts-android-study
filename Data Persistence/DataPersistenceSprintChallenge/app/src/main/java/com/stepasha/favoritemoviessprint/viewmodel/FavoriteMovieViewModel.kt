package com.stepasha.favoritemoviessprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stepasha.favoritemoviessprint.model.FavoriteMovie
import com.stepasha.favoritemoviessprint.repo


class FavouriteMoviesViewModel : ViewModel() {

    val favouriteMovies: LiveData<List<FavoriteMovie>> by lazy {
        readAllFavouriteMovies()
    }

    fun readAllFavouriteMovies() : LiveData<List<FavoriteMovie>> {
        return repo.readAllFavouriteMovies()
    }

    fun createFavouriteMovie(favouriteMovie: FavoriteMovie) {
        repo.createFavouriteMovie((favouriteMovie))
    }

    fun updateFavouriteMovie(favouriteMovie: FavoriteMovie) {
        repo.updateFavouriteMovie(favouriteMovie)
    }

    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie) {
        repo.deleteFavouriteMovie(favouriteMovie)
    }
}