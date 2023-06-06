package com.techworkscc.data.news

import com.techworkscc.BuildConfig

class NewsRepository(private val service: NewsService) {
    suspend fun loadNews(): NewsResponse {
        val conts = Consts()
        val source = when (BuildConfig.FLAVOR.lowercase()) {
            Consts.FlavorSourceEnum.ABC.name.lowercase() -> Consts.FlavorSourceEnum.ABC.source
            Consts.FlavorSourceEnum.BBC.name.lowercase() -> Consts.FlavorSourceEnum.BBC.source
            Consts.FlavorSourceEnum.NFL.name.lowercase() -> Consts.FlavorSourceEnum.NFL.source
            else -> Consts.FlavorSourceEnum.BBC.source

        }
        return service.getNews(
            sourceName = source,
            key = conts.ApiKey
        )
    }
}