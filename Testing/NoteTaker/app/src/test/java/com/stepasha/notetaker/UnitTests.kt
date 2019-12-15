package com.stepasha.notetaker


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.stepasha.notetaker.fragments.ViewFragment
import com.stepasha.notetaker.models.NotesModel
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.android.controller.ActivityController


@RunWith(JUnit4::class)
public class UnitTests {

    // ActivityController is a Robolectric class that drives the Activity lifecycle
    private var controller: ActivityController<MainActivity>? = null
    lateinit var activity: MainActivity
    //initializing fragment
    lateinit var frag1 : ViewFragment


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
    fun frag_should_not_be_null(){
        val frag1 = ViewFragment()
        Assert.assertNotNull(frag1)
    }


    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        val myObjectUnderTest = NotesModel(0,"Test Note","myNote", "05/05/2020")

        // ...when the string is returned from the object under test...
        val result: String = NotesModel(0,"Test Note","myNote", "05/05/2020").toString()

        // ...then the result should be the expected one.
        var FAKE_STRING = NotesModel(0,"Test Note","myNote", "05/05/2020").toString()
        Assert.assertNotNull(FAKE_STRING)
    }
}


