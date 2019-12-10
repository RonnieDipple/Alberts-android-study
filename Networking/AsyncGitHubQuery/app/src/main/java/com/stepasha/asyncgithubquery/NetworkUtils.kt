package com.stepasha.asyncgithubquery

import android.net.Uri
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

object NetworkUtils {
    const val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
    const val PARAM_QUERY = "q"
    /*

     * The sort field. One of stars, forks, or updated.

     * Default: results are sorted by best match if no field is specified.

     */
    const val PARAM_SORT = "sort"
    const val sortBy = "stars"
    /**
     *
     * Builds the URL used to query GitHub.
     *
     *
     *
     * @param githubSearchQuery The keyword that will be queried for.
     *
     * @return The URL to use to query the GitHub server.
     */
    fun buildUrl(githubSearchQuery: String?): URL? {
        val builtUri: Uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
            .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
            .appendQueryParameter(PARAM_SORT, sortBy)
            .build()
        var url: URL? = null
        try {
            url = URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return url
    }

    /**
     *
     * This method returns the entire result from the HTTP response.
     *
     *
     *
     * @param url The URL to fetch the HTTP response from.
     *
     * @return The contents of the HTTP response.
     *
     * @throws IOException Related to network and stream reading
     */
    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL?): String? {
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        return try {
            val `in`: InputStream = urlConnection.getInputStream()
            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")
            val hasInput: Boolean = scanner.hasNext()
            if (hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection.disconnect()
        }
    }
}