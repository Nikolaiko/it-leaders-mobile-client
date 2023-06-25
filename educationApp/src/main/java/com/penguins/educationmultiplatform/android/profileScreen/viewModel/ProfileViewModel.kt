package com.penguins.educationmultiplatform.android.profileScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.mappers.toProfileState
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileScreenState
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.auth.LogoutUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedIndependentUserGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.rootGraph
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserDataUseCase,
    private val navigation: AppNavigation
): ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LogOut -> logout()
            is ProfileEvent.ClickCategory -> clickCategory(event.category)
            ProfileEvent.OpenMenu -> _state.tryEmit(
                _state.value.copy(
                    isMenuVisible = true
                )
            )
            ProfileEvent.CloseMenu -> _state.tryEmit(
                _state.value.copy(
                    isMenuVisible = false
                )
            )
            ProfileEvent.ChangeImage -> changeImage()
        }
    }

    private fun changeImage() {

    }

    private suspend fun getUser() {
        when (val user = getUserUseCase.invoke()) {
            is AppActionResult.Success -> _state.tryEmit(
               user.result.toProfileState()
            )
            is AppActionResult.Fail -> Unit
        }
    }

    private fun clickCategory(category: Category) {
        val map = mutableMapOf<Category, Boolean>()
        for ((currentCategory, isSelected) in _state.value.categories) {
            when (currentCategory == category) {
                true -> map[currentCategory] = !isSelected
                false -> map[currentCategory] = isSelected
            }
        }

        _state.tryEmit(
            _state.value.copy(categories = map)
        )
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
            navigation.navigateTo(
                nonLoggedIndependentUserGraph,
                NavOptions.Builder().setPopUpTo(rootGraph, true).build()
            )
        }
    }
}