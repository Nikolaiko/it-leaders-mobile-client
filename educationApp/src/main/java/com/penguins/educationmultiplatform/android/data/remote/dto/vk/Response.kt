package com.penguins.educationmultiplatform.android.data.remote.dto.vk

@kotlinx.serialization.Serializable
data class Response(
    val count: Int,
    val items: List<Item>
)