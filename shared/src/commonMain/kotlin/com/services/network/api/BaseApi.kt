package com.services.network.api

import com.model.ActionResult
import com.model.network.NetworkError
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

internal open class BaseApi {
    protected fun <T>processInternalError(response: HttpResponse): ActionResult<T, NetworkError> {
        return when(response.status) {
            HttpStatusCode.InternalServerError -> ActionResult.Fail(NetworkError.InternalServerError)
            else -> ActionResult.Fail(NetworkError.UnknownResponse)
        }
    }
}