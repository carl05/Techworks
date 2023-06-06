package com.techworkscc.data.news

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sourceName: String,
        @Query("apiKey") key: String
    ): NewsResponse
}