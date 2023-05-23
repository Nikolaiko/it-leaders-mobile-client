package com.penguins.educationmultiplatform.android.authScreen.data

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Email

data class RegisterScreenUiState(
    val photoUri: Uri? = null,
    val email: String = "",
    val name:String = "",
    val password:String = "",
    val age:Int? = null
)
