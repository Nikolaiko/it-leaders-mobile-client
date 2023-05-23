package com.penguins.educationmultiplatform.android.domain.localUserDataRepository

import com.penguins.educationmultiplatform.android.data.model.auth.UserTokens

interface LocalUserDataRepository {
    fun setSkippedAuthorization(value: Boolean)
    fun getSkippedAuthorization(): Boolean

    fun getTokens(): UserTokens?
    fun setTokens(tokens: UserTokens)
}