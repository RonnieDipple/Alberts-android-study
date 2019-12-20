package com.stepasha.cryptofirstrx.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.stepasha.cryptofirstrx.fragments.BaseFragment
import com.stepasha.cryptofirstrx.fragments.HomeFragment
import com.stepasha.cryptofirstrx.fragments.SecondFragment

class FragmentAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): BaseFragment {
        when (position) {
            0 -> {
                return HomeFragment.newInstance("USD,CAD,EUR,BRL")
            }
            1 -> {
                return SecondFragment.newInstance("USD,CAD,EUR,BRL")
            }
        }

        return BaseFragment()
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
