package com.stepasha.retrofitgithubquery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
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



        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = RepoListAdapter(items)


        val url = "https://api.github.com/search/repositories?q=mario+language:kotlin&sort=stars&order=desc"

        /**
         * doAsync() is part of a Domain Specfic Language or DSL provided by the Kotlin library Anko
         * which provides a simple way to execute code on a thread other than the main thread,
         * with the option to return to the main thread by calling uiThread().
         */
        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }
    }

}
