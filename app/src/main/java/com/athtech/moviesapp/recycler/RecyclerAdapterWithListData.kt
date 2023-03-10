package com.athtech.moviesapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athtech.moviesapp.R


class RecyclerAdapterWithListData : RecyclerView.Adapter<RecyclerHolderWithListData> {


    private var dataList: List<ListData>
    private var context: Context
    private var listener: OnItemClickListener
    private val inflater: LayoutInflater


    constructor(context: Context, dataList: List<ListData>, listener: OnItemClickListener) {
        this.dataList = dataList
        this.context = context
        this.listener = listener
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolderWithListData {
        val view: View = LayoutInflater.from(context).inflate(R.layout.holder_item, parent, false)
        return RecyclerHolderWithListData(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolderWithListData, position: Int) {
        holder.bind(dataList.get(position), listener)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


}