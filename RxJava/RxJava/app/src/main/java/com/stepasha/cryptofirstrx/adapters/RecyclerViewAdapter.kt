package com.stepasha.cryptofirstrx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stepasha.cryptofirstrx.R
import com.stepasha.cryptofirstrx.model.CryptoData

import com.stepasha.cryptofirstrx.model.Price
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoData>,
    private val listener: Listener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(dataModel: CryptoData)
    }

    private val icons: Array<Int> = arrayOf(R.drawable.btc, R.drawable.eth, R.drawable.ltc)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(cryptoList[position], listener, icons, position)

    }

    override fun getItemCount(): Int = cryptoList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dataModel: CryptoData, listener: Listener, icons: Array<Int>, position: Int) {

            itemView.setOnClickListener { listener.onItemClick(dataModel) }
            itemView.icon_view.setImageResource(icons[position])
            itemView.text_name.text = dataModel.name
            itemView.text_price.text = getPricesString(dataModel.prices)
        }

        private fun getPricesString(prices: List<Price>): String {
            var finalString = ""
            for (price in prices) {
                finalString += "${price.currency}: ${price.price}\n"
            }

            return finalString
        }

    }

}