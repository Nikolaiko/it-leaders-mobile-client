package com.penguins.educationmultiplatform.android.authScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel:ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenUiState())
    val state = _state.asStateFlow()


    fun onEvent(event: RegisterScreenEvents){
        when(event){
            is RegisterScreenEvents.SetPhotoUri -> {
                _state.tryEmit(_state.value.copy(photoUri = event.uri))
            }
            is RegisterScreenEvents.AuthWithVK -> TODO()
            is RegisterScreenEvents.SetAgeField -> {
                _state.tryEmit(_state.value.copy(age = event.age))
            }
            is RegisterScreenEvents.SetNameField -> {
                _state.tryEmit(_state.value.copy(name = event.text))
            }
            is RegisterScreenEvents.SetPasswordField -> TODO()
        }
    }
}