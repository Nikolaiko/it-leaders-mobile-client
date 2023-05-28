package com.penguins.educationmultiplatform.android.authScreen.data

import android.net.Uri
import java.util.Date

sealed class RegisterScreenEvents{
    class SetPhotoUri(val uri: Uri?): RegisterScreenEvents()
    class SetNameField(val text: String): RegisterScreenEvents()
    class SetBirthField(val birthDate: Date): RegisterScreenEvents()
    class SetEmailField(val email: String): RegisterScreenEvents()
    class SetPasswordField(val password: String): RegisterScreenEvents()
    class AuthWithVK(val token: String): RegisterScreenEvents()
    object RegisterUser: RegisterScreenEvents()
    object AuthLater: RegisterScreenEvents()
}
