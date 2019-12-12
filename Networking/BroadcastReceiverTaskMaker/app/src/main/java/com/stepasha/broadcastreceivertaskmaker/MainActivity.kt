package com.stepasha.broadcastreceivertaskmaker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //make a task list
    private val taskList = mutableListOf<String>()
    // bind it to the adapter
    private val adapter by lazy { makeAdapter(taskList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, TaskDetailActivity::class.java)
        startActivityForResult(intent, Companion.ADD_TASK_REQUEST)
        //this binds textview to the list view
        taskListView.adapter = adapter
        //set on click listener for each item on the list yet another lambda function
        taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->


        }



    }
    //this is how to receive stuff from an intent
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Companion.ADD_TASK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val task = data?.getStringExtra(TaskDetailActivity.TASK_DESCRIPTION)
                task?.let {
                    taskList.add(task)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }





    //setting up on click function through xml
    fun addTaskClicked(view: View) {}
//text view instead of card view (this is Kotlin Lambda(higher function operation))
    private fun makeAdapter(list: List<String>): ArrayAdapter<String> =
        ArrayAdapter(this, R.layout.list_item, list)

    companion object {
        private const val ADD_TASK_REQUEST = 1
    }
}
