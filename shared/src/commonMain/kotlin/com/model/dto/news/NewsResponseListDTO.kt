package com.model.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseListDTO(
    val news: List<NewsResponseDTO>
)
