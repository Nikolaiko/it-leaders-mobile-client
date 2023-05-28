package com.penguins.educationmultiplatform.android.domain.localUserDataRepository

import com.penguins.educationmultiplatform.android.authScreen.data.UserTokens

interface LocalUserDataRepository {
    fun setSkippedAuthorization(value: Boolean)
    fun getSkippedAuthorization(): Boolean

    fun getTokens(): UserTokens?
    fun setTokens(tokens: UserTokens)

    fun logoutUser()
}