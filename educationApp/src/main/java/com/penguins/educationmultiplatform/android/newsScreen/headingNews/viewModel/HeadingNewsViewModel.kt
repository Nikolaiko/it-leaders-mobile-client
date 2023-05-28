package com.penguins.educationmultiplatform.android.newsScreen.headingNews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsByParamsUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.data.HeadingNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.data.HeadingNewsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeadingNewsViewModel(
    val navigation: NewsNavigation,
    val getNewsByFilters: GetNewsByParamsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HeadingNewsUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun onEvent(event: HeadingNewsEvents) {
        when (event) {
            is HeadingNewsEvents.SetTitleAndNews -> {
                _state.tryEmit(
                    _state.value.copy(
                        category = Category.values().first { event.category == it.title },
                        title = event.title
                    )
                )
                viewModelScope.launch {
                    setNewsList(
                        categories = _state.value.category?.let { listOf(it) },
                        heading = _state.value.title
                    )
                }
            }

            is HeadingNewsEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )

            HeadingNewsEvents.BackButton -> navigation.back()

            HeadingNewsEvents.SearchButton -> navigation.navigateTo(
                NewsScreens.SearchNewsScreen(_state.value.category?.title)
            )
        }
    }

    private suspend fun setNewsList(categories: List<Category>?, heading: String?) {
        when (val response = getNewsByFilters(categories = categories, heading = heading)) {
            is ActionResult.Success -> saveNews(response.result)
            is ActionResult.Fail -> _errorState.tryEmit(response.failure)
        }
    }

    private fun saveNews(list: List<News>) {
        _state.value = _state.value.copy(
            news = list
        )
    }

}
