package com.stepasha.cryptofirstrx.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stepasha.cryptofirstrx.App
import com.stepasha.cryptofirstrx.DetailActivity
import com.stepasha.cryptofirstrx.R
import com.stepasha.cryptofirstrx.adapters.RecyclerViewAdapter
import com.stepasha.cryptofirstrx.model.CryptoData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.util.HalfSerializer.onError
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
//TODO 5: Constant Values for Initial Delay and Interval
const val INITIAL_DELAY_IN_MILLISECONDS: Long = 1000
const val INTERVAL_IN_MILLISECONDS: Long = 10000
open class BaseFragment : Fragment(), RecyclerViewAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    private val viewModel = App.injectCryptoDataViewModel()
    //TODO 11: Declare Disposables (empty disposable -CompositeDisposable)
    private val disposables = CompositeDisposable()
    private lateinit var cryptocurrencyList: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    private var currencies: String = ""

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            currencies = bundle.getString("currencies").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        readBundle(getArguments())

        initRecyclerView(view)

        return view
    }

    override fun onStart() {
        super.onStart()

        loadData()

        Log.d("OnStart", "LoadData")
    }

    override fun onResume() {
        super.onResume()

        loadData()

        Log.d("onResume", "LoadData")
    }

    override fun onRefresh() {
        loadData()

        Log.d("onRefresh", "LoadData")
    }

    override fun onPause() {
        super.onPause()

        //TODO 13: Clear Disposables
        disposables.clear()
        Log.d("onPause", "Clear Disposables")
    }

    override fun onStop() {
        super.onStop()

        //TODO 14: Clear Disposables
        disposables.clear()

        Log.d("onStop", "Clear Disposables")
    }

    private fun initRecyclerView(view: View) {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        cryptocurrencyList = view.findViewById(R.id.recycle)
        cryptocurrencyList.layoutManager = layoutManager

        mSwipeRefreshLayout = view.findViewById(R.id.swipeContainer)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)
    }

    private fun loadData() {
        //TODO 6: Call API using Observable
        // 1
        val disposable = Observable.interval(INITIAL_DELAY_IN_MILLISECONDS, INTERVAL_IN_MILLISECONDS,
            TimeUnit.MILLISECONDS)
            // 2 mainTread because thats where the list is that needs to be observed
            .observeOn(AndroidSchedulers.mainThread())
            // 3 update and error
            .subscribe(this::updateCryptoData, this::onError)
        //TODO 12: Add Disposables
        disposables.add(disposable)
    }

    //TODO 7: Add Update Crypto Data

    private fun updateCryptoData(aLong: Long) {
        // 1 have layout start refreshing
        mSwipeRefreshLayout.isRefreshing = true
        // 2 set observable on data in the list thats being updated
        val observable: Observable<List<CryptoData>> = viewModel.getCryptoData(currencies)
        // 3
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("updateCryptoData", "Received UIModel $it users.")
                handleResponse(it)
            }, {
                handleError(it)
            })
    }
    private fun handleError(t: Throwable) {
        Log.d("handlleError", "Error: $t")
    }
    //TODO 8: Add onError
    private fun onError(throwable: Throwable) {
        Log.d("onError", "OnError in Observable Time: $throwable")
    }
    //TODO 9: Handle API Response & Error
    private fun handleResponse(cryptoDataList: List<CryptoData>) {
        // 1 set the adapter
        recyclerViewAdapter = RecyclerViewAdapter(ArrayList(cryptoDataList), this)
        cryptocurrencyList.adapter = recyclerViewAdapter
        // 2 dont refresh
        mSwipeRefreshLayout.isRefreshing = false
        Log.d("handleResponse", "We have ${disposables.size()} disposables")
        //TODO 12: Add Disposables
    }

    override fun onItemClick(dataModel: CryptoData) {

            //TODO 10: Handle Item Click
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("CryptoName", dataModel.name)
            startActivity(intent)

    }

}
