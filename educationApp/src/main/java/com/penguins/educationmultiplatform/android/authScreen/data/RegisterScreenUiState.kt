package com.penguins.educationmultiplatform.android.authScreen.data

import android.net.Uri

data class RegisterScreenUiState(
    val photoUri: Uri? = null,
    val name: String = "",
    val password: String = "",
    val email: String = "",
    val age: String = "",
    val loading: Boolean = false
)
