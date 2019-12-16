package com.stepasha.notetaker.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stepasha.notetaker.R
import com.stepasha.notetaker.sql.DataHelper
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.add_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(), View.OnClickListener {
//initialize db
    lateinit var mydb : DataHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.add_fragment, container,false)
        //attach db to view
        mydb=DataHelper(context)
        view.btn_Save.setOnClickListener(this)
        return view
    }



    fun saveNote(){
        val title : String = et_Title.text.toString().trim()
        if (TextUtils.isEmpty(title)){
            et_Title.error = "Please, Insert a Title!"
            return
        }
        var note : String = et_Notes.text.toString().trim()
        if(TextUtils.isEmpty(note)){
            et_Notes.error = "Please, Insert a Note!"
            return
        }
//initiating date within saving the note
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date : String = sdf.format(Date())
//once inserted populate and let me know
        var isInserted : Boolean = mydb.addNote(title,note,date)
        if(isInserted){
            Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()
            et_Notes.setText("")
            et_Title.setText("")
        }
    }
    override fun onClick(v: View?) {
        saveNote()
    }
}