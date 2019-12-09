package com.stepasha.sharedprefsfordao

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.stepasha.sharedprefsfordao.App.Companion.prefs
import com.stepasha.sharedprefsfordao.BookData.Companion.createBookEntry
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {

        const val NEW_ENTRY_REQUEST = 2

        const val EDIT_ENTRY_REQUEST = 1

    }

    private var entryList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this is grabbing the intent from edit book activity
        button.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, EditBookActivity::class.java)
            val entry = createBookEntry()
            intent.putExtra(Book.TAG, entry)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)

        }


        entryList = prefs!!.readAllEntries()
        textView.removeAllViews()
        entryList.forEach { entry->
            textView.addView(createBookEntry(entry))
        }
    }
    private fun createBookEntry(entry: Book): TextView {
        val view = TextView(this@MainActivity)
        view.text = "${entry.id}${entry.title}, \n ${entry.reasonToRead} \n ${entry.hasBeenRead}"
        view.setPadding(15, 15, 15, 15)
        view.textSize = 22f
        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == NEW_ENTRY_REQUEST) {
            if (data != null) {
                val entry = data.getSerializableExtra(Book.TAG) as Book
                entryList.add(entry)
                prefs!!.createEntry(entry)
                textView.addView(createBookEntry(entry))
            }
        } else if (requestCode == EDIT_ENTRY_REQUEST) {
            if (data != null) {
                val entry = data.getSerializableExtra(Book.TAG) as Book
                entryList[entry.id] = entry
                prefs!!.updateEntry(entry)
            }
        }

    }

}
