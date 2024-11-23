package com.cmc.regret_aos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter : PagingDataAdapter<FeedData, FeedViewHolder>(FeedDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.feed_item_layout, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}

class FeedDiffUtil : DiffUtil.ItemCallback<FeedData>() {
    override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
        return oldItem == newItem
    }
}

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
    private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
    private val createdDateTextView: TextView = itemView.findViewById(R.id.createdDateTextView)

    fun bind(feedData: FeedData) {
        authorTextView.text = feedData.author
        contentTextView.text = feedData.content
        createdDateTextView.text = feedData.created.toString() // You might want to format the date here
    }
}