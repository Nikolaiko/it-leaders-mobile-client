package com.penguins.educationmultiplatform.android.profileScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileScreenState
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.usecases.auth.LogoutUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.rootGraph
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileEvent
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
                rating = "165 баллов"
            )
        )
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LogOut -> logout()
            is ProfileEvent.ClickCategory -> clickCategory(event.category)
            ProfileEvent.Menu -> Unit
            ProfileEvent.ChangeImage -> Unit
        }
    }

    private fun clickCategory(category: Category) {
        val map = mutableMapOf<Category, Boolean>()
        _state.value.categories.forEach { (currentCategory, isSelected) ->
            when (currentCategory == category) {
                true -> map[category] = !isSelected
                false -> map[category] = isSelected
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
                nonLoggedUserGraph,
                NavOptions.Builder().setPopUpTo(rootGraph, true).build()
            )
        }
    }
}