package com.penguins.educationmultiplatform.android.data.model.auth

data class AuthStatus(
    val skippedAuth: Boolean,
    val tokens: UserTokens? = null
)
