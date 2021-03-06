package com.stepasha.dependencyinjection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import com.stepasha.dependencyinjection.R
import com.stepasha.dependencyinjection.model.Songs
import kotlinx.android.synthetic.main.item_view.view.*


class RecyclerViewAdapter(private val data: Array<Songs>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.song_image
        val itemName: TextView = view.song_name
        val itemPrice: TextView = view.song_author
        val card: CardView = view.direction_card_view

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        context = parent.context
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(data[position].image_link)
            .resize(80, 80)
            .centerCrop()
            .into(holder.imageView)
        holder.itemName.text = data[position].brand
        holder.itemPrice.text = data[position].name
    }
}