package com.example.news_application

import com.example.news_application.R
import android.content.Intent
import android.net.Uri
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

    fun cutDate(date: String): String {
        return (date.substring(0, 10).split("-").reversed().joinToString("."))
    }
    inner class NewsViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.apply {
                newsTitle.text = news.title
                newsTitle.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                    itemView.context.startActivity(intent)
                }
                newsSource.text = binding.root.context.getString(R.string.Source, news.source.name)
                newsAuthor.text = binding.root.context.getString(R.string.Author, news.author)
                newsDate.text = binding.root.context.getString(R.string.Date, cutDate(news.publishedAt))
//                newsDate.text = news.publishedAt
            }
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }
}
