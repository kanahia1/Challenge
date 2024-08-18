package com.kanahia.challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kanahia.challenge.models.DataModel


class DataRecyclerAdapter() :
    PagedListAdapter<DataModel, DataRecyclerAdapter.ViewHolder>(DataModelDiffCallback()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.imageView)
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        var saleTextView: TextView = itemView.findViewById(R.id.saleTextView)

        fun bind(dataModel: DataModel){

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v)
    }

    class DataModelDiffCallback : DiffUtil.ItemCallback<DataModel>() {

        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

}