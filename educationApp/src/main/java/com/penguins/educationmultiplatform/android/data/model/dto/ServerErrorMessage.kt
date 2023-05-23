package com.penguins.educationmultiplatform.android.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorMessage(
    val message: String
)
