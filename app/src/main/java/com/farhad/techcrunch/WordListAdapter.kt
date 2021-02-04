package com.farhad.techcrunch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.farhad.techcrunch.model.NewsItem

class NewsListAdapter(val glide: RequestManager, private val delegate: NewsItemDelegate) :
    ListAdapter<NewsItem, NewsListAdapter.NewsViewHolder>(NewsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        view.setOnClickListener { v -> delegate.onClick(v) }
        return NewsViewHolder(view)
    }

    fun getNewsItem(position: Int) = getItem(position)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val descTextView: TextView = itemView.findViewById(R.id.desc)

        fun bind(item: NewsItem) {
            glide
                .load(item.urlToImage)
                .centerCrop()
                .override(800)
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            titleTextView.text = item.title
            descTextView.text = item.description
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.author == newItem.author
                    && oldItem.title == newItem.title
                    && oldItem.content == newItem.content
        }
    }

    interface NewsItemDelegate {
        fun onClick(v: View)
    }
}