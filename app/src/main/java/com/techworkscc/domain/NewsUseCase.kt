package com.techworkscc.domain

import com.techworkscc.data.news.NewsRepository
import com.techworkscc.data.news.NewsResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsUseCase(private val repository: NewsRepository) {
    suspend fun loadNews(): ListNewsVO {
        return toVO(repository.loadNews())
    }

    fun toVO(response: NewsResponse): ListNewsVO {
        if (response.status != "ok") {
            throw BusinessException()
        } else {
            val voList: MutableList<NewsVO> = mutableListOf()
            val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
//            2023-06-04T19:22:19.5734456Z
            response.articles.map {
                voList.add(
                    NewsVO(
                        imageCover = it.urlToImage,
                        publicationDate = LocalDateTime.parse(it.publishedAt.split(".")[0], pattern),
                        title = it.title
                    )
                )
            }
            return ListNewsVO(voList)
        }
    }
}