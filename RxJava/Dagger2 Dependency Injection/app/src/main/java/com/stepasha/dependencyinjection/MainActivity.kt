package com.stepasha.dependencyinjection


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.stepasha.dependencyinjection.adapter.RecyclerViewAdapter
import com.stepasha.dependencyinjection.model.Songs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable

    @Inject
    lateinit var makeupService: RetrofitNetworkInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)


        recycle.setHasFixedSize(true)
        recycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)



        searchButton.setOnClickListener {

            val searchedFor = enterText.text.toString()

            if (searchedFor.isNotEmpty()) {

                disposable = makeupService.getMakeup(searchedFor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ brand ->
                        if (brand.isNotEmpty()) {
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
}
