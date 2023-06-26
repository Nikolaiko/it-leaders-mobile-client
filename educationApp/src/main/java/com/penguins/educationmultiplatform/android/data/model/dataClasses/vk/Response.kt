package com.penguins.educationmultiplatform.android.data.model.dataClasses.vk

@kotlinx.serialization.Serializable
data class Response(
    val count: Int,
    val items: List<Item>
)