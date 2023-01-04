package com.athtech.moviesapp.recycler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.athtech.moviesapp.MovieActivity
import com.athtech.moviesapp.R
import com.bumptech.glide.Glide

class  RecyclerAdapter : RecyclerView.Adapter<RecyclerHolder> {

    var onItemCliCk : ((MovieActivity) -> Unit) ? =null

    private var dataList: List<String>
    private var context: Context

    constructor(context: Context,dataList: List<String>) {
        this.dataList = dataList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.holder_item, parent, false)
        return RecyclerHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(dataList.get(position))

//        holder.itemView.setOnClickListener{
//         onItemCliCk?.invoke(MovieActivity())
//        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}