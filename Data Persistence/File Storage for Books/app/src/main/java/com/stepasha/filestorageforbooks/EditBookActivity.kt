package com.stepasha.filestorageforbooks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    private var entry: Book = Book(Book.INVALID_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val intent = intent
        entry = intent.getSerializableExtra(Book.TAG) as Book

        entry_id_label.setText("${entry.id}")
        journal_entry_text.setText("${entry.reasonToRead}")

        add.setOnClickListener {
            val resultIntent = Intent()
            var wholeEntry = journal_entry_text.text.toString()
            var wholeEntryTitle = textTitle.text.toString()
            var textSwitch = textSwitch.isChecked

            entry.title = wholeEntryTitle
            entry.reasonToRead = wholeEntry
            entry.hasBeenRead = textSwitch
            entry.id = entry_id_label.text.toString().trim().toInt()
            resultIntent.putExtra(Book.TAG, entry)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()



        }
    }
}
