package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsListUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListUiState
import com.penguins.educationmultiplatform.android.newsScreen.common.data.CategoryNewsList
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val navigation: NewsNavigation,
    private val getNewsUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsListUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    init {
        viewModelScope.launch {
            setNewsList()
        }
    }

    fun onEvent(event: NewsListEvents) {
        when (event) {
            is NewsListEvents.SetCategoryList -> {
                _state.tryEmit(_state.value.copy(list = event.list))
            }

            is NewsListEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )

            is NewsListEvents.OpenCategory -> navigation.navigateTo(
                NewsScreens.CategoryNewsScreen(event.category)
            )

            is NewsListEvents.OpenSearchNews -> navigation.navigateTo(
                NewsScreens.SearchNewsScreen()
            )
        }
    }

    private suspend fun setNewsList() {
        when (val response = getNewsUseCase()) {
            is ActionResult.Success -> saveNews(response.result)
            is ActionResult.Fail -> _errorState.tryEmit(response.failure)
        }
    }

    private fun saveNews(list: List<CategoryNewsList>) {
        _state.value = _state.value.copy(
            lastNews = list.last().news.firstOrNull(),
            list = list.toPairList()
        )
    }

    fun List<CategoryNewsList>.toPairList() = this
        .map {
            Pair(it.category?.title ?: EMPTY_STRING, it.news)
        }

}
