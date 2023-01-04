package com.athtech.moviesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val imageView = findViewById<ImageView>(R.id.mBackdrop)
        val imageView2 = findViewById<ImageView>(R.id.mPoster)
        val rating_tv = findViewById<TextView>(R.id.mRating)
        val title_tv = findViewById<TextView>(R.id.mTitle)
        val overview_tv = findViewById<TextView>(R.id.mOverview)
        val casting_tv = findViewById<TextView>(R.id.mCasting)
        val release_tv = findViewById<TextView>(R.id.mRelease)


        val bundle = intent.extras
        val mTitle = bundle!!.getString("title")
        val mBackdrop = bundle.getString("backdrop_image")
        val mOverView = bundle.getString("overview")
        val mRating = bundle.getString("vote_average")
        val mCasting = bundle.getString("casting")
        val mRelease = bundle.getString("release_date")
        val mPoster = bundle.getString("poster_image")

        Glide.with(this).load(mBackdrop).into(imageView)
        Glide.with(this).load(mPoster).into(imageView2)
        rating_tv.text = mRating
        title_tv.text = mTitle
        overview_tv.text = mOverView
        casting_tv.text = mCasting
        release_tv.text = mRelease


    }

}