package com.stepasha.retrofitgithubquery

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
private val repoRetriever = Retro()
    private val callback = object : Callback<RepoResult>{
        override fun onFailure(call: Call<RepoResult>, t: Throwable) {
            Log.e("MainActivity", "cant call the API")
        }

        override fun onResponse(call: Call<RepoResult>, response: Response<RepoResult>) {
            response.isSuccessful.let {
                val resultList = RepoResult(response.body()?.items ?: emptyList())
                recycle.adapter = RepoListAdapter(resultList)
            }
        }
    }


    private val items = listOf(
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refresh.setOnClickListener {
            repoRetriever.getRepositories(callback)
        }



        recycle.layoutManager = LinearLayoutManager(this)

        val url =
            "https://api.github.com/search/repositories?q=mario+language:kotlin&sort=stars&order=desc"

        /**
         * doAsync() is part of a Domain Specfic Language or DSL provided by the Kotlin library Anko
         * which provides a simple way to execute code on a thread other than the main thread,
         * with the option to return to the main thread by calling uiThread().
         */

        if (isNetworkConnected()) {
           //doAsync {
           //   val result = Request(url).run()
           //    uiThread {
           //        recycle.adapter = RepoListAdapter(result)
           //    }
           //}
            repoRetriever.getRepositories(callback)

        } else {
            AlertDialog.Builder(this).setTitle("No connection")
                .setMessage("please check connection")
                .setPositiveButton(android.R.string.ok){_,_->}
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
