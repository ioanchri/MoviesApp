package com.athtech.moviesapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.athtech.moviesapp.recycler.RecyclerActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        var intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnSearchClickListener {
            searchView.isIconified = false
        }

    }




}