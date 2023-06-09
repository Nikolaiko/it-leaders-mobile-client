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
                rating = "165 баллов",
                selectedCategories = Category.values().toList()
            )
        )
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LogOut -> logout()
            is ProfileEvent.ClickCategory -> clickCategory(event.category)
        }
    }

    private fun clickCategory(category: Category) {
        val list = mutableListOf<Category>()
        var isSelect = true
        _state.value.selectedCategories.forEach { item ->
            when (category != item) {
                true -> list.add(item)
                false -> isSelect = false
            }
        }
        if (isSelect) {
            list.add(category)
        }
        _state.tryEmit(
            _state.value.copy(selectedCategories = list)
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