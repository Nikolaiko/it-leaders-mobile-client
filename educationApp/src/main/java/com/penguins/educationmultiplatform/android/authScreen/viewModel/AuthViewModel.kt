package com.penguins.educationmultiplatform.android.authScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenUiState
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.auth.UserTokens
import com.penguins.educationmultiplatform.android.data.model.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.usecases.LoginWithEmailUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val navigation: AppNavigation,
    private val localStorage: LocalUserDataRepository,
    private val loginWithEmailUseCase: LoginWithEmailUseCase
): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun onEvent(event: AuthScreenEvents){
        when(event) {
            is AuthScreenEvents.JoinWithVK -> TODO()
            is AuthScreenEvents.AuthWithEmail -> authWithEmail()
            is AuthScreenEvents.RegisterButton -> {
                navigation.navigateTo(AppScreens.RegisterScreenRoute)
            }
            is AuthScreenEvents.SetLogin -> {
                _state.tryEmit(_state.value.copy(login = event.text))
            }
            is AuthScreenEvents.SetPassword -> {
                _state.tryEmit(_state.value.copy(password = event.text))
            }
            is AuthScreenEvents.AuthLater -> {
                localStorage.setSkippedAuthorization(true)
                navigation.navigateTo(AppScreens.MainAppScreen)
            }
        }
    }

    fun validateFields(): Boolean {
        return _state.value.login.isNotEmpty()
                && _state.value.password.isNotEmpty()
    }

    private fun authWithEmail() {
        viewModelScope.launch {
            val request = AuthRequest(_state.value.login, _state.value.password)
            val response = loginWithEmailUseCase.invoke(request)
            when(response) {
                is ActionResult.Success -> saveTokens(response.result)
                is ActionResult.Fail -> {
                    _errorState.tryEmit(response.failure)
                }
            }
        }
    }

    private fun saveTokens(auth: AuthResponse) {
        localStorage.setTokens(UserTokens(accessToken = auth.accessToken))
        navigation.navigateTo(AppScreens.MainAppScreen)
    }
}