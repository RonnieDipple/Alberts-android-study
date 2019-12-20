package com.stepasha.cryptofirstrx.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stepasha.cryptofirstrx.R

class SecondFragment: BaseFragment() {

    companion object {
        fun newInstance(currencies: String): SecondFragment {
            val bundle = Bundle()
            bundle.putString("currencies", currencies)

            val fragment = SecondFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

}