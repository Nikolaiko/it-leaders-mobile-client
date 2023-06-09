package com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.auth.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedAsChildUserGraph
import com.penguins.educationmultiplatform.android.testsScreen.data.InitialTestsScreenState
import com.penguins.educationmultiplatform.android.testsScreen.data.TestsStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InitialTestsScreenViewModel(
    private val checkAuthStateUseCase: CheckAuthStateUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val appNavigation: AppNavigation
): ViewModel() {
    private var currentState = InitialTestsScreenState(
        testsCheckStatus = TestsStatus.NotStarted,
    )
    private val _state = MutableStateFlow(currentState)
    val state = _state.asStateFlow()

    fun authUser() {
        appNavigation.navigateTo(nonLoggedAsChildUserGraph)
    }

    fun checkAuthState() {
        viewModelScope.launch {
            currentState = currentState.copy(testsCheckStatus = TestsStatus.Checking)
            _state.emit(currentState)

            val state = checkAuthStateUseCase.invoke()
            when(state.tokens) {
                null -> {
                    currentState = currentState.copy(testsCheckStatus = TestsStatus.NotLoggedIn)
                    _state.emit(currentState)
                }
                else -> checkUserInterests()
            }
        }
    }

    private suspend fun checkUserInterests() {
        when(val userDataAction = getUserDataUseCase.invoke()) {
            is ActionResult.Success -> { }
            is ActionResult.Fail -> {
                currentState = currentState.copy(testsCheckStatus = TestsStatus.FailedWithError)
                _state.emit(currentState)
            }
        }
    }
}