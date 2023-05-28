package com.penguins.educationmultiplatform.android.authScreen.data

data class AuthStatus(
    val skippedAuth: Boolean,
    val tokens: UserTokens? = null
)
