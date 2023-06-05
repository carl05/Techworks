package com.techworkscc.data.news

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sourceName: String = "bbc-news",
        @Query("apiKey") key: String = "603c059f38984dabaeed636bb14283f3"
    ): NewsResponse
}