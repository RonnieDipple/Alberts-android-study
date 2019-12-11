package com.stepasha.asyncgithubquery

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mSearchResultsTextView.setOnClickListener {

            makeGithubSearchQuery()
        }


    }

    private fun makeGithubSearchQuery() {
        val githubQuery: String = mSearchBoxEditText.text.toString()
        val githubSearchUrl: URL? = NetworkUtils.buildUrl(githubQuery)
        mUrlDisplayTextView.text = githubSearchUrl.toString().replace(",", "\n")
        var githubSearchResults: String? = null
        GithubQueryTask(this).execute(githubSearchUrl)

        //try {
        //    githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl)
        //    mSearchResultsTextView.setText(githubSearchResults)
        //} catch (e: IOException) {
        //    e.printStackTrace()
        //}

    }
    class GithubQueryTask (private var activity: MainActivity?): AsyncTask<URL, Void, String>() {


        override fun doInBackground(vararg params: URL?): String? {

            val searchUrl: URL? = params[0]
            var githubSearchResults: String? = null
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl)
            }catch (e: IOException){
                e.printStackTrace()
            }
            return githubSearchResults
        }

        override fun onPreExecute() {
            activity?.progressBar?.visibility = View.VISIBLE
        }
        override fun onPostExecute(result: String?) {
            if (result != null && result != "") {
                activity?.progressBar?.visibility = View.GONE
                activity?.mSearchResultsTextView?.text = result.toString().trim()

            }
        }
    }
    class SimpleThread: Thread() {
        public override fun run() {


        }
    }
    // Implementing the Runnable interface to implement threads.
    class SimpleRunnable: Runnable {
        public override fun run() {

            println("${Thread.currentThread()} has run.")
        }
    }
    //fun main(args: Array<String>) {
    //    val thread = SimpleThread(this)
    //    thread.start() // Will output: Thread[Thread-0,5,main] has run.
    //    val runnable = SimpleRunnable()
    //    val thread1 = Thread(runnable)
    //    thread1.start() // Will output: Thread[Thread-1,5,main] has run
    //}

}


