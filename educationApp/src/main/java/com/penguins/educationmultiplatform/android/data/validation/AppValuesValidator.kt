package com.penguins.educationmultiplatform.android.data.validation

import android.text.TextUtils
import com.penguins.educationmultiplatform.android.data.model.consts.minPasswordLength
import com.penguins.educationmultiplatform.android.domain.validation.ValuesValidator

class AppValuesValidator: ValuesValidator {
    override fun validateEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun validatePassword(password: String): Boolean = password.length >= minPasswordLength
}