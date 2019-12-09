package com.stepasha.filestorageforbooks


import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


class Book: Serializable {

    companion object {

        const val TAG = "BookEntry Entry"

        const val INVALID_ID = -1

    }





    var id: Int = 0

    var title: String?

    var reasonToRead: String? = null

    var hasBeenRead: Boolean= false





    constructor(id: Int){

        this.id = id

        this.title = " "

        this.reasonToRead = " "

        this.hasBeenRead = false

    }

    constructor(csvString: String){

        val values = csvString.split(",")

        //  if (values.size == -1)

        try {

            this.id = Integer.parseInt(values[0].trim())

        }catch (e: NumberFormatException){

            e.printStackTrace()

        }

        this.title = values[1].trim()

        this.reasonToRead = values[2].trim()

        this.hasBeenRead = values[3].trim().toBoolean()

    }

    internal fun toCSVString(): String{

        return "$id, $title, $reasonToRead, $hasBeenRead"

    }



    override fun toString(): String {

        return "BookEntry Entry(id:$id, title:$title, reason to read: $reasonToRead, was it read before: $hasBeenRead)"

    }


    constructor(jsonObject: JSONObject){

        //set ALLLL the values

        try {

            this.id = jsonObject.getInt("id")

        }catch (e: JSONException){

            this.id = 1

        }

        try {

            this.title = jsonObject.getString("title")

        }catch (e: JSONException){

            this.title = " "

        }

        try {

            this.reasonToRead = jsonObject.getString("reasonToRead")//this end has to match what ever this as constant would be.

        }catch (e: JSONException){

            this.reasonToRead = " "

        }

        try {

            this.hasBeenRead = jsonObject.getBoolean("hasBeenRead")

        }catch (e: JSONException){

            this.hasBeenRead = false

        }

    }



    // TODO 6: implement to Jsonobject method from application.kt

    fun toJsonObject(): JSONObject?{

        try {

            return JSONObject().apply {

                put("id", id)

                put("title", title)

                put("reasonToRead", reasonToRead)

                put("hasBeenRead", hasBeenRead)

            }

        }catch (e: JSONException){

            return try {

                JSONObject("{ \"id\" : \"$id\" \"title\" : \"$title\" \"reason to read\" : \"$reasonToRead\", \"has been read\": \"$hasBeenRead\"}")

            }catch (e2: JSONException){

                e2.printStackTrace()

                return null

            }

        }

    }



}