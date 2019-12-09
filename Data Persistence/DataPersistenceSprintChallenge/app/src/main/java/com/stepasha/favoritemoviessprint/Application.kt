package com.stepasha.favoritemoviessprint

import android.app.Application
import com.stepasha.favoritemoviessprint.database.FavoriteMovieRepoInterface
import com.stepasha.favoritemoviessprint.database.FavouriteMovieDBRepo


val repo: FavoriteMovieRepoInterface by lazy {
    App.repo!!
}

class App : Application() {


    companion object {
        var repo: FavoriteMovieRepoInterface? = null
    }

    override fun onCreate() {
        super.onCreate()
        repo = FavouriteMovieDBRepo(applicationContext)
    }
}