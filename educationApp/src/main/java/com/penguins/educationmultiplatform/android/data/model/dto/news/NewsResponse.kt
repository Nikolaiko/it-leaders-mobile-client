package com.penguins.educationmultiplatform.android.data.model.dto.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val id: Long? = null,
    val title: String? = null,
    val info: String? = null,
    val heading: String? = null,
    val category: String? = null,
    val imageUrl: String? = null,
)
