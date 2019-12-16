package com.stepasha.notetaker.models

import java.io.Serializable

open class NotesModel  : Serializable{
    var id: Int
    lateinit var title: String
    lateinit var note: String
    lateinit var date: String

    constructor(id : Int, title : String, note : String, date : String) : super()  {
        this.id=id
        this.title=title
        this.note=note
        this.date=date
    }
}