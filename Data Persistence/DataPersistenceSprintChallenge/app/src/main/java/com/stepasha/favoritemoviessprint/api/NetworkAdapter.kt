package com.stepasha.favoritemoviessprint.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


object NetworkAdapter {
    val GET = "GET"
    val POST = "POST"
    val PUT = "PUT"
    val DELETE = "DELETE"
    val TIMEOUT = 3000

    // only handles get and post requests right now
    fun getBitmapFromURL(urlString: String): Bitmap? {
        var result: Bitmap? = null
        val stream: InputStream? = null
        var connection: HttpURLConnection? = null

        try {
            val url = URL(urlString)
            connection = url
                .openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            result = BitmapFactory.decodeStream(input)

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close()

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            connection?.disconnect()
        }
        return result
    }
}