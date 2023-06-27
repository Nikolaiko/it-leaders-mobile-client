package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.network.NetworkError
import com.penguins.educationmultiplatform.android.data.model.error.AppError

fun NetworkError.toAppError() = when(this) {
    is NetworkError.NoError -> AppError.NoError
    is NetworkError.UnauthorizedAccess -> AppError.UnauthorizedAccess
    is NetworkError.InternalServerError -> AppError.InternalServerError
    is NetworkError.UnknownResponse -> AppError.UnknownResponse
    is NetworkError.UserAlreadyExist -> AppError.UserAlreadyExist
    is NetworkError.UserNotFound -> AppError.UserNotFound
    is NetworkError.WrongToken -> AppError.WrongToken
    is NetworkError.VKLoginFailed -> AppError.VKLoginFailed

}