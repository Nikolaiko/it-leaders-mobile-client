package com.penguins.educationmultiplatform.android.data.model.dataClasses.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsListResponse(
    val news: List<NewsResponse>
)
