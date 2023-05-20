package com.penguins.educationmultiplatform.android.authScreen.data

sealed class AuthScreenEvents {
    class SetLogin(val text:String):AuthScreenEvents()
    class SetPassword(val text:String):AuthScreenEvents()
    class JoinWithVK(val token:String):AuthScreenEvents()
    class RegisterButton():AuthScreenEvents()
}