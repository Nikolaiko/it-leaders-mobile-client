package com.penguins.educationmultiplatform.android.domain.localUserDataRepository

import com.penguins.educationmultiplatform.android.authScreen.data.UserTokens
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData

interface LocalUserDataRepository {
    fun setSkippedAuthorization(value: Boolean)
    fun getSkippedAuthorization(): Boolean

    fun getTokens(): UserTokens?
    fun setTokens(tokens: UserTokens)

    fun saveUserData(inputUserData: LocalUserData)
    fun getUserData(): LocalUserData?

    fun saveUserScore(newScore: Int)
    fun getUserScore(): Int


    fun logoutUser()
}