package com.example.news_application

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.news_application.databinding.ItemNewsBinding
import androidx.recyclerview.widget.ListAdapter

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.apply {
                newsTitle.text = news.title
                newsUrl.text = news.url
                newsSource.text = news.source
                newsDate.text = news.date
                newsKeywords.text = news.keywords.joinToString(", ")
            }
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }
}
