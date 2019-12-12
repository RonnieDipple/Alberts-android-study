package com.stepasha.retrofitgithubquery

import android.util.Log
import java.net.URL

class Request(private val url: String) {

    fun run() {
        //this writes the responses to log cat
        val repoListJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, repoListJsonStr)
    }
}