package com.stepasha.jsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private var stringBuilder: StringBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //quite unclear why this would be needed in Kotlin(further research)
        textView = findViewById(R.id.textView)
        /**
        this how the file store is used
        big aha moment. file storage is used when you wanna store files directly
        onto the device(of course with the owners permission)
    */
        val fileName = cacheDir.absolutePath + "PostJson.json"
        //write the file
        writeJSONtoFile(fileName)
        //read it from file
        readJSONfromFile(fileName)
        }
        //fun to write the file
        private fun writeJSONtoFile(string: String){
            //this is creating a list of posts
            var posts = ArrayList<String>()
            posts.add("This is the tutorial on how to read JSON from file")
            posts.add("Converting JSON to GSON")
            posts.add("also using file storage")

            var poster = Post("JSON Tutorial","www.building-locator.com","Albert Yakubov", posts)

            var gson = Gson()
            var JSONtoString : String = gson.toJson(poster)
            //write to storage
            val file = File(string)
            file.writeText(JSONtoString)
        }
    private fun readJSONfromFile(file: String){
        var gson = Gson()
        val bufferedReader: BufferedReader = File(file).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }

        //now convert the file back to JSON
        var post = gson.fromJson(inputString, Post::class.java)

        stringBuilder = StringBuilder("Post Details\n------------------------------------")
        + Log.d("Kotlin", "My Posts")
        stringBuilder?.append("\nPost Heading:" + post.postHeading)
        stringBuilder?.append("\nPost URL:" + post.postUrl)
        stringBuilder?.append("\nPOst Author:"+ post.postAuthor)
        stringBuilder?.append("\nPosts:")

        post.posts?.forEach {
            stringBuilder?.append("$it,")

            textView?.text = stringBuilder.toString()
        }
    }
}
