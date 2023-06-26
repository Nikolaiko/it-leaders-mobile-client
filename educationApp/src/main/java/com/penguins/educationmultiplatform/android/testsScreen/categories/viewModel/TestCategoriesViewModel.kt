package com.penguins.educationmultiplatform.android.testsScreen.categories.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategory
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.SaveUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.UpdateUserInterestsUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.TestsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.TestsScreens
import com.penguins.educationmultiplatform.android.testsScreen.data.CategoriesScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestCategoriesViewModel(
    private val testsNavigation: TestsNavigation,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val updateUserInterestsUseCase: UpdateUserInterestsUseCase
): ViewModel() {
    private var currentState = CategoriesScreenState()
    private var userData: LocalUserData? = null

    private val _state = MutableStateFlow(currentState)
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun initUserData() {
        viewModelScope.launch {
            when(val response = getUserDataUseCase.invoke()) {
                is AppActionResult.Success -> {
                    userData = response.result
                    val interests = userData?.interests ?: emptyList()
                    currentState = currentState.copy(
                        musicSelected = interests.contains(InterestCategory.music),
                        artSelected = interests.contains(InterestCategory.art),
                        theatereSelected = interests.contains(InterestCategory.theatre),
                        danceSelected = interests.contains(InterestCategory.choreography),
                        canContinue = interests.isNotEmpty()
                    )
                    _state.emit(currentState)
                }
                is AppActionResult.Fail -> _errorState.tryEmit(response.failure)
            }
        }
    }

    fun proceedToTests() {
        userData?.apply(::updateUserInterests)

        val options = NavOptions
            .Builder()
            .setPopUpTo(TestsScreens.TestsCategoriesScreen.route, true)
            .build()
        testsNavigation.navigateTo(TestsScreens.UserTestsScreen, options)
    }

    fun toggleMusic() {
        currentState = currentState.copy(musicSelected = !currentState.musicSelected)
        _state.tryEmit(currentState)
        checkContinueState()
    }

    fun toggleDance() {
        currentState = currentState.copy(danceSelected = !currentState.danceSelected)
        _state.tryEmit(currentState)
        checkContinueState()
    }

    fun toggleArt() {
        currentState = currentState.copy(artSelected = !currentState.artSelected)
        _state.tryEmit(currentState)
        checkContinueState()
    }

    fun toggleTheatere() {
        currentState = currentState.copy(theatereSelected = !currentState.theatereSelected)
        _state.tryEmit(currentState)
        checkContinueState()
    }

    private fun checkContinueState() {
        val canContinue = currentState.artSelected
                || currentState.musicSelected
                || currentState.danceSelected
                || currentState.theatereSelected
        currentState = currentState.copy(canContinue = canContinue)
        _state.tryEmit(currentState)
    }

    private fun getInterestsList(): List<InterestCategory> {
        val userInterests = mutableListOf<InterestCategory>()
        if (currentState.musicSelected) {
            userInterests.add(InterestCategory.music)
        }
        if (currentState.theatereSelected) {
            userInterests.add(InterestCategory.theatre)
        }
        if (currentState.danceSelected) {
            userInterests.add(InterestCategory.choreography)
        }
        if (currentState.artSelected) {
            userInterests.add(InterestCategory.art)
        }
        return userInterests.toList()
    }

    private fun updateUserInterests(userData: LocalUserData) {
        viewModelScope.launch {
            val interestsList = getInterestsList()
            val newUserData = userData.copy(interests = interestsList)

            saveUserDataUseCase.invoke(newUserData)
            val response = updateUserInterestsUseCase.invoke(interestsList)
            when(response) {
                is AppActionResult.Success -> {  }
                is AppActionResult.Fail -> {
                    _errorState.tryEmit(response.failure)
                }
            }
        }
    }
}