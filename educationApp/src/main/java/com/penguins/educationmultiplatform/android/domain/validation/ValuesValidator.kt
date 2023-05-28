package com.penguins.educationmultiplatform.android.domain.validation

interface ValuesValidator {
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
}