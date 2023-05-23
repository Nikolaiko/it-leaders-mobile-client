package com.penguins.educationmultiplatform.android.data.model.error

sealed class AppError {
    object NoError: AppError()
    object UserAlreadyExist: AppError()
    object UserNotFound: AppError()
    object InternalServerError: AppError()
    object UnknownResponse: AppError()

}