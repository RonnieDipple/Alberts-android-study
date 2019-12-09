package com.stepasha.favoritemoviessprint

import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.stepasha.favoritemoviessprint.api.MovieApi
import com.stepasha.favoritemoviessprint.api.MovieConstants
import com.stepasha.favoritemoviessprint.model.FavoriteMovie
import com.stepasha.favoritemoviessprint.model.MovieSearchResult
import com.stepasha.favoritemoviessprint.viewmodel.FavouriteMoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {


    lateinit var viewModel: FavouriteMoviesViewModel
    lateinit var movieService: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search_movie.setOnClickListener {
            movieService = MovieApi.Factory.create()
            getMovieByName(et_movie.text.toString())
        }
        btn_view_favourites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
        viewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel::class.java)
    }

    private fun getMovieByName(movieName: String) {
        movieService.getMoviesbyName(movieName, MovieConstants.API_KEY_PARAM).enqueue(this)
    }

    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

    }

    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if (response.isSuccessful) {
            if (response.body() != null) {
                var movieList = response.body()?.results
                movieList?.forEach {
                    layout_list.addView(createTextView(it.title, it.id))
                }
            }
        }
    }

    fun createTextView(movieTitle: String, id: Int): TextView {
        val view = TextView(this)
        view.text = movieTitle
        view.textSize = 24f
        view.tag = id
        view.setOnClickListener {
            view.setBackgroundColor(Color.GRAY)
            val favouriteMovie = FavoriteMovie(movieTitle, false, id)
            CreateAsyncTask(viewModel).execute(favouriteMovie)
        }
        return view
    }
    class CreateAsyncTask(viewModel: FavouriteMoviesViewModel) : AsyncTask<FavoriteMovie, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg favouriteMovies:FavoriteMovie?) {
            if (favouriteMovies.isNotEmpty()) {
                favouriteMovies[0]?.let {
                    viewModel.get()?.createFavouriteMovie(it)
                }
            }
        }
    }


}

