package com.penguins.educationmultiplatform.android.authScreen.data

import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.VKProfile

sealed class AuthScreenEvents {
    class SetLogin(val text: String): AuthScreenEvents()
    class SetPassword(val text: String): AuthScreenEvents()
    class JoinWithVK(val token: String, val email: String, val profile: VKProfile): AuthScreenEvents()
    object LoginVKFailed: AuthScreenEvents()
    object RegisterButton: AuthScreenEvents()
    object AuthLater: AuthScreenEvents()
    object AuthWithEmail: AuthScreenEvents()
}