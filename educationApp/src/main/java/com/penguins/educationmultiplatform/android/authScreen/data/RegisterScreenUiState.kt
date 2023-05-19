package com.penguins.educationmultiplatform.android.authScreen.data

import android.net.Uri

data class RegisterScreenUiState(
    val photoUri: Uri? = null,
    val name:String = "",
    val password:String = "",
    val age:Int? = null
)
