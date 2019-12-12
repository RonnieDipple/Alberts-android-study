package com.stepasha.broadcastreceivertaskmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_TASK_REQUEST = 1

    //make a task list
    private val taskList = mutableListOf<String>()
    // bind it to the adapter
    private val adapter by lazy { makeAdapter(taskList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this binds textview to the list view
        taskListView.adapter = adapter
        //set on click listener for each item on the list yet another lambda function
        taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->


        }
    }
    //setting up on click function through xml
    fun addTaskClicked(view: View) {}
//text view instead of card view (this is Kotlin Lambda(higher function operation))
    private fun makeAdapter(list: List<String>): ArrayAdapter<String> =
        ArrayAdapter(this, R.layout.list_item, list)
}
