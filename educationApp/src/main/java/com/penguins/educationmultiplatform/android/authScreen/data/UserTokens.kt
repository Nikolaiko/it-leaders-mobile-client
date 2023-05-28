package com.penguins.educationmultiplatform.android.authScreen.data

import kotlinx.serialization.Serializable

@Serializable
data class UserTokens(
    val accessToken: String
    //val refreshToken
)
