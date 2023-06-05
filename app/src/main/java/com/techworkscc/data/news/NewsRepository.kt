package com.techworkscc.data.news

import androidx.lifecycle.LiveData

class NewsRepository(private val service: NewsService) {
    suspend fun loadNews(): NewsResponse {
        return service.getNews()
    }
}