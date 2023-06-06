package com.techworkscc.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ListNewsVO (
    val listNewsVO: List<NewsVO>
): Parcelable
@Parcelize
data class NewsVO(
    val sourceName: String,
    val title: String,
    val publishedAt: LocalDateTime,
    val urlToImage: String,
    val description: String,
    val content: String
): Parcelable