package com.model.network

sealed class NetworkError {
    object NoError: NetworkError()
    object UnauthorizedAccess: NetworkError()
    object UserAlreadyExist: NetworkError()
    object UserNotFound: NetworkError()
    object InternalServerError: NetworkError()
    object UnknownResponse: NetworkError()
    object VKLoginFailed: NetworkError()
    object UnprocessableEntry: NetworkError()
}
