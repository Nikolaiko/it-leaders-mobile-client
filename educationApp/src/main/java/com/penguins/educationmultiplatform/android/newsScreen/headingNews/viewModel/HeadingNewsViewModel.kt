package com.penguins.educationmultiplatform.android.newsScreen.headingNews.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.data.HeadingNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.data.HeadingNewsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HeadingNewsViewModel(
    val navigation: NewsNavigation,
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
            }

            is HeadingNewsEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )

            HeadingNewsEvents.BackButton -> navigation.back()

            HeadingNewsEvents.SearchButton -> navigation.navigateTo(NewsScreens.SearchNewsScreen)
        }
    }

}
