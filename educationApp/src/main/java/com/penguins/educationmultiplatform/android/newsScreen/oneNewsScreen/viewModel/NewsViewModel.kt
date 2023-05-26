package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugOneNews
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel(
    private val navigation: NewsNavigation
) : ViewModel() {

    private val _state = MutableStateFlow(NewsUiState())
    val state = _state.asStateFlow()

    fun setNews(news: News?) {
        _state.value = _state.value.copy(
            news = news
        )
    }

    fun onEvent(event: NewsEvents) {
        when (event) {
            is NewsEvents.SetNews -> _state.tryEmit(_state.value.copy(news = event.news))
            NewsEvents.BackButton -> navigation.back()
        }
    }

}
