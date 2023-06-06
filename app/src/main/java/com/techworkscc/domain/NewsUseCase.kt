package com.techworkscc.domain

import com.techworkscc.data.news.NewsRepository
import com.techworkscc.data.news.NewsResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsUseCase(private val repository: NewsRepository) {
    val datePatternNews = "yyyy-MM-dd'T'HH:mm:ss"
    suspend fun loadNews(): ListNewsVO {
        return toVO(repository.loadNews())
    }

    fun toVO(response: NewsResponse): ListNewsVO {
        if (response.status != "ok") {
            throw BusinessException()
        } else {
            val voList: MutableList<NewsVO> = mutableListOf()
            val pattern = DateTimeFormatter.ofPattern(datePatternNews)
            response.articles.map {
                voList.add(
                    NewsVO(
                        urlToImage = it.urlToImage,
                        publishedAt = LocalDateTime.parse(it.publishedAt.substring(0,19), pattern),
                        title = it.title,
                        content = it.content,
                        description = it.description,
                        sourceName = it.source.name
                    )
                )
            }
            return ListNewsVO(
                voList.sortedBy { it.publishedAt })
        }
    }
}