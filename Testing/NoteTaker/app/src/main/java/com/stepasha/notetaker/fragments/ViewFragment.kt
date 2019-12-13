package com.stepasha.notetaker.fragments

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.stepasha.notetaker.R
import com.stepasha.notetaker.adapter.RecyclerViewForNotesAdapter
import com.stepasha.notetaker.models.NotesModel
import com.stepasha.notetaker.sql.DataHelper
import kotlinx.android.synthetic.main.view_fragment.view.*


class ViewFragment : Fragment() {

    lateinit var mydb : DataHelper
    lateinit var listdata : MutableList<NotesModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.view_fragment, container,false)
        mydb= DataHelper(context)
        view.recyclerview.setHasFixedSize(true)
        view.recyclerview.layoutManager = GridLayoutManager(context,2)
        listdata = ArrayList()

            getData()

        view.recyclerview.adapter =RecyclerViewForNotesAdapter(listdata,view.context)

        return view
    }
    fun getData(){
        val res : Cursor =mydb.readAllNote()
        if(res.count==0){
            Toast.makeText(context,"Your Note is Empty!",Toast.LENGTH_SHORT).show()
            listdata.add(NotesModel(0, "This is Test Title", "notes go in here, \n to start create new note", "5/5/2020"))
            return
        }
        while (res.moveToNext()){

            val dataNotes : NotesModel = NotesModel(res.getInt(0),res.getString(1)
                ,res.getString(2),res.getString(3).substring(0,10))

            listdata.add(dataNotes)
        }
    }
}