package com.athtech.moviesapp.recycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.athtech.moviesapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class RecyclerHolderWithListData(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ListData, listener: com.athtech.moviesapp.recycler.OnItemClickListener) {
      //  var textView = itemView.findViewById<TextView>(R.id.holder_text)
     //   textView.setText("Title: " + data.movieTitle + System.lineSeparator() + "Release Date: " + data.movieRelease + System.lineSeparator() + "Rating: " + data.movieRating + System.lineSeparator() + "Overview: " + data.movieOverview)
        var textView = itemView.findViewById<TextView>(R.id.holder_title)
        textView.setText(data.movieTitle)
        var textView2 = itemView.findViewById<TextView>(R.id.holder_release)
        textView2.setText("Released at: " + data.movieRelease)
        var textView3 = itemView.findViewById<TextView>(R.id.holder_rating)
        textView3.setText("Rating: " + data.movieRating  + " " + data.movieId)
        var textView4 = itemView.findViewById<TextView>(R.id.holder_overview)
        textView4.setText("Overview: " + data.movieOverview)





        itemView.findViewById<LinearLayout>(R.id.holder_container).setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("Holder", "user Pressed the row ${data.movieRelease}" + data)
                listener.onClick(data)
            }

        })

        var imageView = itemView.findViewById<ImageView>(R.id.holder_img)
        val URL = data.movieBackdrop

        Glide.with(itemView)
            .load(URL)
            .transform(CenterCrop())
            .into(imageView)
    }
}