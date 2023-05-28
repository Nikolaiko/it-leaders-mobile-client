package com.penguins.educationmultiplatform.android.data.model.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsListResponse(
    val news: List<NewsResponse>
)
