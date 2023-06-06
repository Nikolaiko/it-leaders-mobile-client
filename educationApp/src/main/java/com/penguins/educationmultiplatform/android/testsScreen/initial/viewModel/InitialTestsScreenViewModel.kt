package com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.auth.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedAsChildUserGraph
import com.penguins.educationmultiplatform.android.testsScreen.data.InitialTestsScreenState
import com.penguins.educationmultiplatform.android.testsScreen.data.TestCaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InitialTestsScreenViewModel(
    private val checkAuthStateUseCase: CheckAuthStateUseCase,
    private val appNavigation: AppNavigation
): ViewModel() {
    private var currentState = InitialTestsScreenState(
        isLoggedIn = checkAuthStateUseCase.invoke().tokens != null
    )
    private val _state = MutableStateFlow(currentState)
    val state = _state.asStateFlow()

    fun checkAuthState() {
        val state = checkAuthStateUseCase.invoke()
        currentState = currentState.copy(isLoggedIn = state.tokens != null)
        _state.tryEmit(currentState)
    }

    fun authUser() {
        appNavigation.navigateTo(nonLoggedAsChildUserGraph)
    }
}