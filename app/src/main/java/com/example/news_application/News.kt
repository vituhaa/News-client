package com.example.news_application
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val version: Int,
    val generatedAt: String,
    val source: String,
    val query: String,
    val count: Int,
    val items: List<News>
)
data class News(
    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("keywords")
    val keywords: List<String>
)
