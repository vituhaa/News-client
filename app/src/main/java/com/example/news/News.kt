package com.example.news
import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("source")
    val source: Source,

    @SerializedName("date")
    val date: String,

    @SerializedName("keywords")
    val keywords: Keywords,
)

data class Source(
    @SerializedName("title")
    val title: String
)

data class Keywords(
    @SerializedName("keywords")
    val keywords: List<String>
)