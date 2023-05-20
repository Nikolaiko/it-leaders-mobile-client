package com.penguins.educationmultiplatform.android.data.remote.dto.profile

@kotlinx.serialization.Serializable
data class Response(
    val first_name: String,
    val bdate:String
)