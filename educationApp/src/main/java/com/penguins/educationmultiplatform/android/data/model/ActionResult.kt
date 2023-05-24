package com.penguins.educationmultiplatform.android.data.model

sealed class ActionResult<out ResultType, out Failure> {
    class Success<out ResultType>(val result: ResultType): ActionResult<ResultType, Nothing>()
    class Fail<out Failure>(val failure: Failure): ActionResult<Nothing, Failure>()
}