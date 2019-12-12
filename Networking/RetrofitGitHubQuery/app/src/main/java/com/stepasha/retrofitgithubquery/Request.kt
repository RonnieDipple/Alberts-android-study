package com.stepasha.retrofitgithubquery

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class Request(private val url: String) {
    companion object {
        private val URL = "https://api.github.com/search/repositories"
        private val SEARCH = "q=mario+language:kotlin&sort=stars&order=desc"
        private val COMPLETE_URL = "$URL?$SEARCH"
    }

    fun run(): RepoResult {
        //this writes the responses to log cat
        val repoListJsonStr = URL(COMPLETE_URL).readText()

        return Gson().fromJson(repoListJsonStr, RepoResult::class.java)
    }
}