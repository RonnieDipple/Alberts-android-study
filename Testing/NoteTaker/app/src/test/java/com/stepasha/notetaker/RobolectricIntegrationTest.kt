package com.stepasha.notetaker

import android.R
import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest="...")
public class RobolectricIntegrationTest() {
    lateinit var activity: MainActivity

    @Before
    @Throws(Exception::class)
    fun setUp() {
       activity = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }
    @Test
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }


    @Test

    fun fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = ViewActivity()
        // When
        // Then
        Assert.assertNotNull(fragment)
    }

}