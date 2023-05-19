package com.penguins.educationmultiplatform.android.data.remote.dto.vk


@kotlinx.serialization.Serializable
data class Size(
    val height: Int,
    val type: String,
    val url: String,
    val width: Int
)