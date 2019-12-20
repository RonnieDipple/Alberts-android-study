package com.stepasha.dependencyinjection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.stepasha.dependencyinjection.Helper
import com.stepasha.dependencyinjection.R
import com.stepasha.dependencyinjection.model.Songs


class SongAdapter(context: Context, allSongs: List<Songs>) :
    RecyclerView.Adapter<SongViewHolder>() {
    private val context: Context = context
    private val allSongs: List<Songs> = allSongs
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val mSong: Songs = allSongs[position]
        holder.songTitle.setText(mSong.songTitle)
        holder.songAuthor.setText(mSong.songAuthor)
        val imagePath: String = Helper.URL.toString() + "images/" + mSong.songImage
        Glide.with(context).load(imagePath).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter()
            .into(holder.songImage)
    }

    override fun getItemCount(): Int {
        return allSongs.size
    }

}