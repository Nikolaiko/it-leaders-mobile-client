package com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.SaveUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.auth.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.TestsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.TestsScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedAsChildUserGraph
import com.penguins.educationmultiplatform.android.testsScreen.data.InitialTestsScreenState
import com.penguins.educationmultiplatform.android.testsScreen.data.TestsStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InitialTestsScreenViewModel(
    private val checkAuthStateUseCase: CheckAuthStateUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val appNavigation: AppNavigation,
    private val testsNavigation: TestsNavigation
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
            is AppActionResult.Success -> processLocalUserData(userDataAction.result)
            is AppActionResult.Fail -> {
                currentState = currentState.copy(testsCheckStatus = TestsStatus.FailedWithError)
                _state.emit(currentState)
            }
        }
    }

    private fun processLocalUserData(userData: LocalUserData) {
        val options = NavOptions
            .Builder()
            .setPopUpTo(TestsScreens.InitialTestsScreen.route, true)
            .build()

        saveUserDataUseCase.invoke(userData)
        when (userData.interests.isNotEmpty()) {
            true -> {
                testsNavigation.navigateTo(
                    TestsScreens.UserTestsScreen,
                    options
                )
            }
            false -> testsNavigation.navigateTo(
                TestsScreens.TestsCategoriesScreen,
                options
            )
        }
    }
}