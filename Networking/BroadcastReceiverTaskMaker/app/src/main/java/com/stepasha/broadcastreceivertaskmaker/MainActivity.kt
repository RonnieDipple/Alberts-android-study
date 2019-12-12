package com.stepasha.broadcastreceivertaskmaker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //make a task list
    private val taskList = mutableListOf<String>()
    // bind it to the adapter
    private val adapter by lazy { makeAdapter(taskList) }
    private val tickReceiver by lazy { makeBroadcastReceiver() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateTimeTextView.text = getCurrentTimeStamp()
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))

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
        private const val LOG_TAG ="MainActivityLog"
    private fun getCurrentTimeStamp():String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
        val now = Date()
        return simpleDateFormat.format(now)
    }
    }
    private fun makeBroadcastReceiver():BroadcastReceiver{
        return object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK){
                    dateTimeTextView.text = getCurrentTimeStamp()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dateTimeTextView.text = getCurrentTimeStamp()
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onPause() {
        super.onPause()
        try {
            unregisterReceiver(tickReceiver)
        }catch (e: IllegalArgumentException){
            Log.e(MainActivity.LOG_TAG, "receiver not registered", e)
        }

    }
}
