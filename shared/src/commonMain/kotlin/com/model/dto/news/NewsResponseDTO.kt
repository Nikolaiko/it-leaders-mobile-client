package com.model.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDTO(
    val id: Long? = null,
    val title: String? = null,
    val info: String? = null,
    val heading: String? = null,
    val category: String? = null,
    val imageUrl: String? = null
)
