package com.stepasha.favoritemoviessprint.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


class MovieSearchResult(val results: List<FavoriteMovie>)


class MovieOverview(







    val original_language: String,



    val original_title: String,



    val overview: String,



    val popularity: Float,



    val poster_path: String,



    val release_date: String,



    val title: String,



    val isVideo: Boolean,



    val vote_average: Float,



    val vote_count: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    val movieId: Int = 0



)