package com.penguins.educationmultiplatform.android.authScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenUiState
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {


    private val _state = MutableStateFlow(AuthScreenUiState())
    val state = _state.asStateFlow()


    fun onEvent(event: AuthScreenEvents){
        when(event){
            is AuthScreenEvents.JoinWithVK -> TODO()
            is AuthScreenEvents.RegisterButton -> TODO()
            is AuthScreenEvents.SetLogin -> {
                _state.tryEmit(_state.value.copy(login = event.text))
            }
            is AuthScreenEvents.SetPassword -> {
                _state.tryEmit(_state.value.copy(password = event.text))
            }
        }
    }
}