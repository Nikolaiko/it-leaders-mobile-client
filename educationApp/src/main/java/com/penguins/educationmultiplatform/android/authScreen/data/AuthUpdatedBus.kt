package com.penguins.educationmultiplatform.android.authScreen.data

import com.penguins.educationmultiplatform.android.domain.navigation.DataBusInterface
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AuthUpdatedBus: DataBusInterface<Boolean> {
    private val _dataFlow: MutableSharedFlow<Boolean> = MutableSharedFlow(1, 1)
    override val dataFlow: SharedFlow<Boolean>
        get() = _dataFlow.asSharedFlow()

    override suspend fun emitData(data: Boolean) {
        _dataFlow.emit(data)
    }
}