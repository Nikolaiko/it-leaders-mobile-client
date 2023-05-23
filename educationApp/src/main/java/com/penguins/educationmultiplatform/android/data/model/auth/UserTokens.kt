package com.penguins.educationmultiplatform.android.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserTokens(
    val accessToken: String
    //val refreshToken
)
