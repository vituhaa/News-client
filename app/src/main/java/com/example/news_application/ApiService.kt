package com.example.news_application

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("headlines.json")
    suspend fun getHeadlines(): Response<NewsResponse>

//    @GET("api/{MM}/{DD}.json")
//    suspend fun getHeadlinesByDate(@Path("MM") MM: String, DD:String): Response<List<News>>
//
//    @GET("api/{MM}/index.json")
//    suspend fun getHeadlinesForMonth(@Path("MM") MM: String, DD:String): Response<List<News>>
//
//    @GET("api/index.json")
//    suspend fun getDateIndex(): Response<List<News>>
}