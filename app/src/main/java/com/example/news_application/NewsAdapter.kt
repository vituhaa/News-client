package com.example.news_application

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.news_application.databinding.ItemNewsBinding
import androidx.recyclerview.widget.ListAdapter

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {
    private val avatarColors = listOf(
        0xFF6C63FF.toInt(), 0xFF43A047.toInt(), 0xFFE53935.toInt(),
        0xFF1E88E5.toInt(), 0xFFFF8F00.toInt(), 0xFF8E24AA.toInt(),
        0xFF00897B.toInt(), 0xFFD81B60.toInt(), 0xFF3949AB.toInt(),
        0xFF00ACC1.toInt()
    )

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
//                newsSource.text = Source.title
                newsDate.text = news.date
//                newsKeywords.text = news.keywords
            }
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }
}
