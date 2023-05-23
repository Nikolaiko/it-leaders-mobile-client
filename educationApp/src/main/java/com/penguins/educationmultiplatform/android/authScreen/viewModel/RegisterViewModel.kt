package com.penguins.educationmultiplatform.android.authScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenUiState
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.auth.UserTokens
import com.penguins.educationmultiplatform.android.data.model.dto.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.destinations.MainScreenViewDestination
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val navigation: AppNavigation,
    private val localStorage: LocalUserDataRepository,
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun onEvent(event: RegisterScreenEvents){
        when(event) {
            is RegisterScreenEvents.SetPhotoUri -> {
                _state.tryEmit(_state.value.copy(photoUri = event.uri))
            }
            is RegisterScreenEvents.SetAgeField -> {
                _state.tryEmit(_state.value.copy(age = event.age))
            }
            is RegisterScreenEvents.SetNameField -> {
                _state.tryEmit(_state.value.copy(name = event.text))
            }
            is RegisterScreenEvents.SetEmailField -> {
                _state.tryEmit(_state.value.copy(email = event.email))
            }
            is RegisterScreenEvents.SetPasswordField -> {
                _state.tryEmit(_state.value.copy(password = event.password))
            }
            is RegisterScreenEvents.AuthLater -> {
                localStorage.setSkippedAuthorization(true)
                navigation.navigateTo(MainScreenViewDestination)
            }
            is RegisterScreenEvents.RegisterUser -> {
                sendUserRegisterRequest()
            }
            is RegisterScreenEvents.AuthWithVK -> TODO()
        }
    }

    fun validateFields(): Boolean {
        return _state.value.age != null
                && _state.value.name.isNotEmpty()
                && _state.value.email.isNotEmpty()
                && _state.value.password.isNotEmpty()
    }

    private fun sendUserRegisterRequest() {
        val registerData = RegisterRequest(
            email = _state.value.email,
            password = _state.value.password,
            name = _state.value.name,
            vkToken = null,
            birthDate = _state.value.age?.toString() ?: "0"
        )

        viewModelScope.launch {
            val registeredResult = registerUserUseCase.invoke(registerData)
            when(registeredResult) {
                is ActionResult.Success -> saveTokens(registeredResult.result as AuthResponse)
                is ActionResult.Fail -> {
                    _errorState.tryEmit(registeredResult.failure)
                }
            }
        }
    }

    private fun saveTokens(auth: AuthResponse) {
        localStorage.setTokens(UserTokens(accessToken = auth.accessToken))
        navigation.navigateTo(MainScreenViewDestination)
    }
}