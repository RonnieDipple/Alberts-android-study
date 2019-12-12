package com.stepasha.broadcastreceivertaskmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {
companion object{
    const val TASK_DESCRIPTION = "task_description"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
    }
    fun doneClicked(view: View){

        //steps to put the data in an intent to pass to next activity
        val taskDescription = descriptionText.text.toString()
        if (!taskDescription.isEmpty()){
            val result = Intent()
            result.putExtra(TASK_DESCRIPTION, taskDescription)
            setResult(Activity.RESULT_OK, result)
        }else{
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
