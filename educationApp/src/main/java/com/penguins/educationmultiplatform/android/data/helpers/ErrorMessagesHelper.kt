package com.penguins.educationmultiplatform.android.data.helpers

import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.error.AppError

fun getAuthErrorMessageFromType(error: AppError): Int {
    return when(error) {
        is AppError.NoError -> R.string.no_error_message
        is AppError.UserNotFound -> R.string.user_not_found_message
        is AppError.UnprocessableEntry -> R.string.wrong_data_format_message
        else -> R.string.unknown_error_message
    }
}

fun getRegisterErrorMessageFromType(error: AppError): Int {
    return when(error) {
        is AppError.NoError -> R.string.no_error_message
        is AppError.UserNotFound -> R.string.user_not_found_message
        is AppError.UnprocessableEntry -> R.string.wrong_data_format_message
        is AppError.UserAlreadyExist -> R.string.user_alredy_exist_message
        else -> R.string.unknown_error_message
    }
}