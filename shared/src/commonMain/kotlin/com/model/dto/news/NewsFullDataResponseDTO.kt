package com.model.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsFullDataResponseDTO(
    val id: Int,
    val content: String
)
