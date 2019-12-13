package com.stepasha.notetaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.stepasha.notetaker.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(fragment = ViewFragment())
        navigation.setOnNavigationItemSelectedListener(this)
    }
    private fun loadFragment(fragment: Fragment):Boolean{

        if(fragment != null) {

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()

            return true

        }

        return false
}
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        var fragment : Fragment = ViewFragment()

        when  (p0.itemId){

            R.id.navigation_view -> fragment= ViewFragment()

            R.id.navigation_add -> fragment= AddFragment()

            R.id.navigation_search -> fragment= SearchFragment()

        }



        return loadFragment(fragment)

    }

}
