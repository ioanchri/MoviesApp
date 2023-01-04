package com.athtech.moviesapp.recycler

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.athtech.moviesapp.R

class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(data: String) {
        var textView = itemView.findViewById<TextView>(R.id.holder_text)
        textView.setText(data)

        var imageView = itemView.findViewById<ImageView>(R.id.holder_img)

    }
}