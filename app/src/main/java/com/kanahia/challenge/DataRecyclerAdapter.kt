package com.kanahia.challenge

import android.content.Context
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
import com.bumptech.glide.Glide
import com.kanahia.challenge.models.DataModel


class DataRecyclerAdapter() :
    PagedListAdapter<DataModel, DataRecyclerAdapter.ViewHolder>(DataModelDiffCallback()) {

    class ViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private var itemImage: ImageView = itemView.findViewById(R.id.imageView)
        private var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private var priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private var saleTextView: TextView = itemView.findViewById(R.id.saleTextView)

        fun bind(dataModel: DataModel) {
            titleTextView.text = dataModel.name
            priceTextView.text = dataModel.price.toString()
            if (dataModel.sold) saleTextView.visibility = View.VISIBLE
            Glide.with(context).load(dataModel.image).into(itemImage)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view, parent.context)
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