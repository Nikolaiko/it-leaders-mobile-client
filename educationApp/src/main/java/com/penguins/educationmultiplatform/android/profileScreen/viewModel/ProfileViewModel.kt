package com.penguins.educationmultiplatform.android.profileScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.profileScreen.data.ProfileScreenState
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.usecases.auth.LogoutUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.rootGraph
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val navigation: AppNavigation
): ViewModel() {
    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        _state.tryEmit(
            _state.value.copy(
                userName = "Владимир Кузнецов",
                age = "22 года",
                rating = "165 баллов",
                selectedCategories = Category.values().toList()
            )
        )
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
            navigation.navigateTo(
                nonLoggedUserGraph,
                NavOptions.Builder().setPopUpTo(rootGraph, true).build()
            )
        }
    }
}