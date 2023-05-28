package com.penguins.educationmultiplatform.android.newsScreen.common.data

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val title: String? = null,
    val imageId: Int? = null,
    val text: String? = null,
    val category: Category? = null,
    val heading: String? = null
)
