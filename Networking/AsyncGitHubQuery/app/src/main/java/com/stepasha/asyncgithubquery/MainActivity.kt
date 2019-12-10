package com.stepasha.asyncgithubquery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun makeGithubSearchQuery() {
        val githubQuery: String = mSearchBoxEditText.getText().toString()
        val githubSearchUrl: URL? = NetworkUtils.buildUrl(githubQuery)
        mUrlDisplayTextView.setText(githubSearchUrl.toString())
        var githubSearchResults: String? = null
        try {
            githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl)
            mSearchResultsTextView.setText(githubSearchResults)
        } catch (e: IOException) {
            e.printStackTrace()
        }
       
    }
}
