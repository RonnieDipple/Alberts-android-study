package com.stepasha.dependencyinjection.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stepasha.dependencyinjection.R


class SongViewHolder : ViewHolder {
    var songTitle: TextView
    var songAuthor: TextView
    var songImage: ImageView

    constructor(
        itemView: View?,
        songTitle: TextView,
        songAuthor: TextView,
        songImage: ImageView
    ) : super(itemView!!) {
        this.songTitle = songTitle
        this.songAuthor = songAuthor
        this.songImage = songImage
    }

    constructor(itemView: View) : super(itemView) {
        songTitle = itemView.findViewById(R.id.song_name)
        songAuthor = itemView.findViewById(R.id.song_author)
        songImage = itemView.findViewById(R.id.song_image) as ImageView
    }
}