package com.stepasha.dependencyinjection


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.stepasha.dependencyinjection.adapter.RecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable

    @Inject
    lateinit var makeupService: RetrofitNetworkInterface

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        (application as App).appComponent.inject(this)


        recycle.setHasFixedSize(true)
        recycle.layoutManager = GridLayoutManager(this, 3)



        searchButton.setOnClickListener {

            val searchedFor = enterText.text.toString()

            if (searchedFor.isNotEmpty() && searchedFor.contains(searchedFor)) {

                disposable = makeupService.getMakeup(searchedFor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ brand ->
                        if (brand.isNotEmpty() ) {
                            recycle.adapter = RecyclerViewAdapter(brand)
                        } else {
                            Toast.makeText(this, "Brand not found", Toast.LENGTH_SHORT).show()
                        }
                    }, { t ->
                        Log.i("Retrofit - ", "$t", t)
                    })
            } else {
                Toast.makeText(this, "Please enter a brand", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Back Button Press")
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SHARE, bundle)
        //custom log message
        Crashlytics.log("Dont press the back button!")
        Crashlytics.getInstance().crash() // Force a crash


    }
}
