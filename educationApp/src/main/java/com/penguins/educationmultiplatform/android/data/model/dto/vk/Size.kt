package com.penguins.educationmultiplatform.android.data.model.dto.vk


@kotlinx.serialization.Serializable
data class Size(
    val height: Int,
    val type: String,
    val url: String,
    val width: Int
)