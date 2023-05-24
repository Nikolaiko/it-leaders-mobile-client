package com.penguins.educationmultiplatform.android.authScreen.data

import android.net.Uri

sealed class RegisterScreenEvents{
    class SetPhotoUri(val uri: Uri?): RegisterScreenEvents()
    class SetNameField(val text: String): RegisterScreenEvents()
    class SetAgeField(val age: Int): RegisterScreenEvents()
    class SetEmailField(val email: String): RegisterScreenEvents()
    class SetPasswordField(val password: String): RegisterScreenEvents()
    class AuthWithVK(val token: String): RegisterScreenEvents()
    object RegisterUser: RegisterScreenEvents()
    object AuthLater: RegisterScreenEvents()
}
