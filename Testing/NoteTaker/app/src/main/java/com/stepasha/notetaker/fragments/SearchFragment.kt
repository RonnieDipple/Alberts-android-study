package com.stepasha.notetaker.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stepasha.notetaker.R
import com.stepasha.notetaker.adapter.RecyclerViewForNotesAdapter
import com.stepasha.notetaker.models.NotesModel
import com.stepasha.notetaker.sql.DataHelper
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*


class SearchFragment : Fragment(), View.OnKeyListener {

    lateinit var mydb : DataHelper
    lateinit var listdata: MutableList<NotesModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.search_fragment, container,false)
        mydb = DataHelper(context)
        listdata= ArrayList()
        view.recyclerviewsearch.setHasFixedSize(true)
        view.recyclerviewsearch.layoutManager = LinearLayoutManager(context)
        view.et_Search.setOnKeyListener(this)

        return view
    }
    fun searchData(){
        listdata.clear()
        val query  = et_Search.text.toString().trim()
        if(TextUtils.isEmpty(query)){
            et_Search.error = "Please, insert a search query!"
            return
        }
        val res = mydb.searchNote(query)
        if(res.count==0){
            Toast.makeText(context,"Can't find the note you're looking for!",Toast.LENGTH_SHORT).show()
            return
        }

        while (res.moveToNext()){
            val dataNotes : NotesModel = NotesModel(res.getInt(0),res.getString(1)
                ,res.getString(2),res.getString(3).substring(0,10))
            listdata.add(dataNotes)
        }
        recyclerviewsearch.adapter= RecyclerViewForNotesAdapter(listdata, this!!.context!!)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if(event.action==KeyEvent.ACTION_DOWN){
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    searchData()
                    return true

                } else if(event.action==KeyEvent.KEYCODE_DEL)
                    listdata.clear()
                return true
            }
        }
        return false
    }
}