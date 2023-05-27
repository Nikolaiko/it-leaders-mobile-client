package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsUiState
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchNewsViewModel(
    private val navigation: NewsNavigation
) : ViewModel() {

    private val _state = MutableStateFlow(SearchNewsUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: SearchNewsEvents) {
        when (event) {
            is SearchNewsEvents.SetSearchingText ->  _state.tryEmit(
                _state.value.copy(searchingText = event.text)
            )

            is SearchNewsEvents.SetSearchingNews -> _state.tryEmit(
                _state.value.copy(findingNews = event.news)
            )

            is SearchNewsEvents.ClearSearch -> _state.tryEmit(
                _state.value.copy(searchingText = EMPTY_STRING)
            )

            is SearchNewsEvents.SearchButton -> Unit//findNewsUseCase

            is SearchNewsEvents.FilterButton -> Unit//open BottomSheet

            is SearchNewsEvents.BackButton -> navigation.back()
        }
    }

}
