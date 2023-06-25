package com.penguins.educationmultiplatform.android.authScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.authScreen.data.AuthDisplayMode
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenUiState
import com.penguins.educationmultiplatform.android.authScreen.data.AuthUpdatedBus
import com.penguins.educationmultiplatform.android.authScreen.data.UserTokens
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.VKProfile
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.auth.LoginWithVKUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.auth.LoginWithEmailUseCase
import com.penguins.educationmultiplatform.android.domain.validation.ValuesValidator
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.mainScreenRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val navigation: AppNavigation,
    private val authUpdatedBus: AuthUpdatedBus,
    private val localStorage: LocalUserDataRepository,
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val loginWithVKUseCase: LoginWithVKUseCase,
    private val fieldValidator: ValuesValidator
): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    private var authMode = AuthDisplayMode.independent

    fun setAuthMode(newValue: AuthDisplayMode) {
        authMode = newValue
    }

    fun onEvent(event: AuthScreenEvents){
        when(event) {
            is AuthScreenEvents.JoinWithVK -> authWithVK(event.token, event.email, event.profile)
            is AuthScreenEvents.LoginVKFailed -> _errorState.tryEmit(AppError.VKLoginFailed)
            is AuthScreenEvents.AuthWithEmail -> authWithEmail()
            is AuthScreenEvents.RegisterButton -> {
                navigation.navigateTo(AppScreens.RegisterScreenRoute)
            }
            is AuthScreenEvents.SetLogin -> {
                _state.tryEmit(_state.value.copy(login = event.text.trim()))
            }
            is AuthScreenEvents.SetPassword -> {
                _state.tryEmit(_state.value.copy(password = event.text))
            }
            is AuthScreenEvents.AuthLater -> authLater()
        }
    }

    fun validateFields(): Boolean {
        return fieldValidator.validateEmail(_state.value.login)
                && fieldValidator.validatePassword(_state.value.password)
    }

    private fun authWithEmail() {
        viewModelScope.launch {
            val request = AuthRequest(_state.value.login, _state.value.password)
            val response = loginWithEmailUseCase.invoke(request)
            when(response) {
                is AppActionResult.Success -> saveTokens(response.result)
                is AppActionResult.Fail -> _errorState.tryEmit(response.failure)
            }
        }
    }

    private fun authWithVK(token: String, email: String, profile: VKProfile) {
        viewModelScope.launch {
            val response = loginWithVKUseCase.invoke(token, email, profile)
            when(response) {
                is AppActionResult.Success -> saveTokens(response.result)
                is AppActionResult.Fail -> _errorState.tryEmit(response.failure)
            }
        }
    }

    private fun saveTokens(auth: AuthResponse) {
        localStorage.setTokens(UserTokens(accessToken = auth.accessToken, refreshToken = auth.refreshToken))
        when(authMode) {
            AuthDisplayMode.independent -> navigation.navigateTo(AppScreens.MainAppScreen)
            AuthDisplayMode.asChild -> navigation.popBackStack(route = mainScreenRoute)
        }
    }

    private fun authLater() {
        localStorage.setSkippedAuthorization(true)
        when(authMode) {
            AuthDisplayMode.independent -> navigation.navigateTo(AppScreens.MainAppScreen)
            AuthDisplayMode.asChild -> navigation.popBackStack(route = mainScreenRoute)
        }
    }
}