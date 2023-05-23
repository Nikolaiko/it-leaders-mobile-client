package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel : ViewModel() {

    private val _state = MutableStateFlow(NewsListUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: NewsListEvents) = when (event) {
        is NewsListEvents.SetCategoryList -> {
            _state.tryEmit(_state.value.copy(list = event.list))
        }
        is NewsListEvents.OpenNews -> Unit
        is NewsListEvents.SetNewsTitle -> {
            _state.tryEmit(_state.value.copy(searchingNews = event.titleNews))
        }
        is NewsListEvents.FilterButton -> Unit
        is NewsListEvents.OpenCategory -> Unit
    }

}
