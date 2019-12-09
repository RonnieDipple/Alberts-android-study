package com.stepasha.favoritemoviessprint

import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stepasha.favoritemoviessprint.model.FavoriteMovie
import com.stepasha.favoritemoviessprint.viewmodel.FavouriteMoviesViewModel
import kotlinx.android.synthetic.main.activity_favorites.*
import java.lang.ref.WeakReference

class FavoritesActivity : AppCompatActivity() {

    lateinit var viewModel: FavouriteMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        viewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel::class.java)
        ReadAllAsyncTask(this).execute()
    }

    private fun updateForEntries(favouriteMovies: List<FavoriteMovie>) {
        layout_favourite_movie_list.removeAllViews()
        favouriteMovies.forEach { favouriteMovie ->
            layout_favourite_movie_list.addView(createTextView(favouriteMovie))
        }
    }

    fun createTextView(favouritemovie: FavoriteMovie): TextView {
        val view = TextView(this)
        view.text = favouritemovie.title
        view.textSize = 24f
        view.tag = favouritemovie.id

        if (favouritemovie.watched)
            view.setBackgroundColor(Color.GRAY)
        else
            view.setBackgroundColor(Color.WHITE)
        view.setOnClickListener {
            if (!favouritemovie.watched) {
                val favouriteMovie = FavoriteMovie(favouritemovie.title, true, favouritemovie.id)
                UpdateAsyncTask(viewModel).execute(favouriteMovie)
            } else if (favouritemovie.watched) {
                view.setBackgroundColor(Color.WHITE)
                val favouriteMovie = FavoriteMovie(favouritemovie.title, false, favouritemovie.id)
                UpdateAsyncTask(viewModel).execute(favouriteMovie)
            }
        }
        view.setOnLongClickListener {
            DeleteAsyncTask(viewModel).execute(favouritemovie)
            return@setOnLongClickListener true
        }
        return view
    }

    class UpdateAsyncTask(viewModel: FavouriteMoviesViewModel) :
        AsyncTask<FavoriteMovie, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg favourieMovies: FavoriteMovie?) {
            if (favourieMovies.isNotEmpty()) {
                favourieMovies[0]?.let {

                    viewModel.get()?.updateFavouriteMovie(it)
                }
            }
        }
    }

    class DeleteAsyncTask(viewModel: FavouriteMoviesViewModel) :
        AsyncTask<FavoriteMovie, Void, Unit>() {

        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg favourieMovies: FavoriteMovie?) {
            if (favourieMovies.isNotEmpty()) {
                favourieMovies[0]?.let {
                    viewModel.get()?.deleteFavouriteMovie(it)
                }
            }
        }
    }

    class ReadAllAsyncTask(activity: FavoritesActivity) :
        AsyncTask<Void, Void, LiveData<List<FavoriteMovie>>?>() {

        private val activity = WeakReference(activity)
        override fun doInBackground(vararg entries: Void?): LiveData<List<FavoriteMovie>>? {
            return activity.get()?.viewModel?.favouriteMovies
        }


        override fun onPostExecute(result: LiveData<List<FavoriteMovie>>?) {
            activity.get()?.let { act ->
                result?.let { entries ->
                    // Observe LiveData here
                    entries.observe(act,
                        Observer<List<FavoriteMovie>> { t ->
                            t?.let {
                                act.updateForEntries(t)
                            }
                        })
                }
            }
        }
    }

}