package com.example.news_application
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)

data class Source(
    val id: String,
    val name: String
)

data class News(
    @SerializedName("source")
    val source: Source,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

//    @SerializedName("urlToImage")
//    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String
)
