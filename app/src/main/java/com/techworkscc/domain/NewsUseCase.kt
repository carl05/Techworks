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
                        imageCover = it.urlToImage,
                        publicationDate = LocalDateTime.parse(it.publishedAt.split(".")[0], pattern),
                        title = it.title
                    )
                )
            }
            return ListNewsVO(voList.sortedBy { it.publicationDate })
        }
    }
}