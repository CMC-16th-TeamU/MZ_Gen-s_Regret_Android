package com.cmc.regret_aos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter : PagingDataAdapter<FeedData, RecyclerView.ViewHolder>(FeedDiffUtil()) {

    companion object {
        private const val LEFT_VIEW = 1
        private const val RIGHT_VIEW = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) LEFT_VIEW else RIGHT_VIEW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            LEFT_VIEW -> {
                val leftView = layoutInflater.inflate(R.layout.feed_left_item_layout, parent, false)
                LeftViewHolder(leftView)
            }
            RIGHT_VIEW -> {
                val rightView = layoutInflater.inflate(R.layout.feed_right_tem_layout, parent, false)
                RightViewHolder(rightView)
            }
            else -> {
                val leftView = layoutInflater.inflate(R.layout.feed_left_item_layout, parent, false)
                LeftViewHolder(leftView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        when (holder) {
            is LeftViewHolder -> holder.bind(item)
            is RightViewHolder -> holder.bind(item)
        }
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

class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

    fun bind(feedData: FeedData) {
        contentTextView.text = feedData.content
    }
}

class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

    fun bind(feedData: FeedData) {
        contentTextView.text = feedData.content
    }
}