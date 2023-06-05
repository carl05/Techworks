package com.techworkscc.domain

import java.time.LocalDateTime

data class ListNewsVO (
    val listNewsVO: List<NewsVO>
)
data class NewsVO(
    val title: String,
    val publicationDate: LocalDateTime,
    val imageCover: String
)