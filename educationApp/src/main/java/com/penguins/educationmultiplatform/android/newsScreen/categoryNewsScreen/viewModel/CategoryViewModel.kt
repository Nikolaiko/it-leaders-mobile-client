package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsByCategoryUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryEvents
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryUiState
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    val navigation: NewsNavigation,
    val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun onEvent(event: CategoryEvents) {
        when (event) {
            is CategoryEvents.SetCategoryAndNews -> {
                viewModelScope.launch {
                    setNewsList(event.category)
                }
            }

            is CategoryEvents.SetLastNews -> _state.tryEmit(
                _state.value.copy(lastNews = event.news)
            )

            is CategoryEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )

            is CategoryEvents.OpenNewsListByHeading -> navigation.navigateTo(
                NewsScreens.HeadingNewsScreen(
                    category = _state.value.category?.title ?: EMPTY_STRING,
                    heading = event.heading
                )
            )

            CategoryEvents.BackButton -> navigation.back()

            CategoryEvents.SearchButton -> navigation.navigateTo(
                NewsScreens.SearchNewsScreen(_state.value.category?.title)
            )
        }
    }

    private suspend fun setNewsList(category: String) {
        when (val response = getNewsByCategoryUseCase(category)) {
            is ActionResult.Success -> {
                _state.value = _state.value.copy(
                    category = response.result.first().category,
                    lastNews = response.result.last(),
                    headingNews = response.result
                        .dropLast(1)
                        .filter { it.heading != null }
                        .groupBy { it.heading!! }
                )
            }
            is ActionResult.Fail -> _errorState.tryEmit(response.failure)
        }
    }

}
