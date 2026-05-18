package com.example.news
import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("title")
    val title: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("body")
    val body: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("dateTimePub")
    val dateTimePub: String,

    @SerializedName("source")
    val source: Source
)

data class Source(
    @SerializedName("title")
    val title: String
)