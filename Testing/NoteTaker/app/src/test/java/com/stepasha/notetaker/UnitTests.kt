package com.stepasha.notetaker


import android.app.Application
import com.stepasha.notetaker.fragments.ViewFragment
import com.stepasha.notetaker.models.NotesModel
import com.stepasha.notetaker.sql.DataHelper
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertSame
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.android.controller.ActivityController
import java.util.*


/**
 * referenced from android documentation @ developer.android.com
 * built around the reference
 */

@RunWith(JUnit4::class)
public class UnitTests {


    // ActivityController is a Robolectric class that drives the Activity lifecycle
    private var controller: ActivityController<MainActivity>? = null
    //initializing activity
    lateinit var activity: MainActivity

     //initializing notes model
    lateinit var notesModel: NotesModel
    //actuals
    private val ALLOWED_ID = 0
    private val ALLOWED_TITLE = "Test"
    private val ALLOWED_NOTE = "Note"
    private val ALLOWed_DATE = "5/5/2020"


    //creates tests based on mock annotations


    @Before
    @Throws(Exception::class)
    fun setUp() {


        //set up activity
        activity = MainActivity()



    }


    @Test
    //checks if activity loads
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(activity)


    }

    //test if activity 2 loads
    @Test

    fun fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = ViewActivity()
        // When
        // Then
        Assert.assertNotNull(fragment)
    }

    //test if fragment loads
    @Test
    fun frag_should_not_be_null() {
        val frag1 = ViewFragment()
        Assert.assertNotNull(frag1)
    }
    @Test
    fun titleTest() {
        val testTitleVal = "Test"
        notesModel = NotesModel(0, "Test", "Note","5/5/2020")

        assertEquals(testTitleVal, notesModel.title, ALLOWED_TITLE)

    }
    @Test
    fun noteTest() {
        val testTitleVal = "Note"
        notesModel = NotesModel(0, "Test", "Note","5/5/2020")

        assertEquals(testTitleVal, notesModel.note, ALLOWED_NOTE)

    }
    @Test
    fun dateTest() {

        val testDateVal = "5/5/2020"
        notesModel = NotesModel(0, "Test", "Note", "5/5/2020")

        assertEquals(testDateVal, notesModel.date, ALLOWed_DATE)

    }
    @Test
    fun idTest() {
//serves as key or a double sided scale
        val testIdVal = "key"
        notesModel = NotesModel(0, "Test", "Note", "5/5/2020")

        assertEquals(testIdVal, notesModel.id, ALLOWED_ID)

    }

}

