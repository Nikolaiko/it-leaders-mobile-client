package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugOneNews
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel : ViewModel() {

    private val _state = MutableStateFlow(NewsUiState())
    val state = _state.asStateFlow()

    init {
        onEvent(NewsEvents.SetNews(getDebugOneNews()))
    }

    fun onEvent(event: NewsEvents) = when (event) {
        is NewsEvents.SetNews -> _state.tryEmit(_state.value.copy(news = event.news))
        NewsEvents.BackButton -> Unit
    }

}
