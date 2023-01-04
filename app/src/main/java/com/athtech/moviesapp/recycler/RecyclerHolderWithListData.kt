package com.athtech.moviesapp.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.athtech.moviesapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class RecyclerHolderWithListData(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ListData) {
        var textView = itemView.findViewById<TextView>(R.id.holder_text)
        textView.setText(data.value1 + System.lineSeparator() + data.value2)

        var imageView = itemView.findViewById<ImageView>(R.id.holder_img)
        val URL = data.value3

        Glide.with(itemView)
            .load(URL)
            .transform(CenterCrop())
            .into(imageView)
    }
}