package com.penguins.educationmultiplatform.android.data.model

sealed class AppActionResult<out ResultType, out Failure> {
    class Success<out ResultType>(val result: ResultType): AppActionResult<ResultType, Nothing>()
    class Fail<out Failure>(val failure: Failure): AppActionResult<Nothing, Failure>()
}