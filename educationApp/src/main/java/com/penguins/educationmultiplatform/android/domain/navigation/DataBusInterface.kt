package com.penguins.educationmultiplatform.android.domain.navigation

import kotlinx.coroutines.flow.SharedFlow

interface DataBusInterface<T> {
    val dataFlow: SharedFlow<T>

    suspend fun emitData(data: T)
}