package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.listOfCategories
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryEvents
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryUiState
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.listOfNewByHeading
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugLastNews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel(
    val navigation: NewsNavigation
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryUiState())
    val state = _state.asStateFlow()

    fun setCategory(category: String?) {
        _state.value = _state.value.copy(
            category = Category.values().firstOrNull { it.title == category },
            lastNews = getDebugLastNews(),
            headingNews = listOfNewByHeading()
        )
    }

    fun onEvent(event: CategoryEvents) {
        when (event) {
            is CategoryEvents.SetLastNews -> _state.tryEmit(
                _state.value.copy(lastNews = event.news)
            )
            is CategoryEvents.SetHeadingNewsList -> _state.tryEmit(
                _state.value.copy(headingNews = event.list)
            )
            is CategoryEvents.SetSchool -> Unit
            is CategoryEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )
            is CategoryEvents.OpenNewsList -> Unit
            CategoryEvents.BackButton -> navigation.back()
            CategoryEvents.SearchButton -> Unit
        }
    }

}
