package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsByParamsUseCase
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.newsScreen.common.data.getCategory
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsUiState
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchNewsViewModel(
    private val navigation: NewsNavigation,
    private val getNewsByParamsUseCase: GetNewsByParamsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchNewsUiState())
    val state = _state.asStateFlow()

    private val _errorState = MutableSharedFlow<AppError>(replay = 2)
    val errorState = _errorState.asSharedFlow()

    fun onEvent(event: SearchNewsEvents) {
        when (event) {
            is SearchNewsEvents.SetSearchingText ->  _state.tryEmit(
                _state.value.copy(searchingText = event.text)
            )

            is SearchNewsEvents.SetSearchingNews -> _state.tryEmit(
                _state.value.copy(findingNews = event.news)
            )

            is SearchNewsEvents.ClearSearch -> _state.tryEmit(
                _state.value.copy(
                    searchingText = EMPTY_STRING,
                    findingNews = emptyList()
                )
            )

            is SearchNewsEvents.SearchButton -> {
                viewModelScope.launch { setNewsByFilter() }
            }

            is SearchNewsEvents.SetCategory -> {
                getCategory(event.category)?.let {
                    _state.tryEmit(
                        _state.value.copy(
                            categories = _state.value.categories.plus(it)
                        )
                    )
                }
            }

            is SearchNewsEvents.SetCategories -> {
                _state.tryEmit(_state.value.copy(categories = event.categories))
            }

            is SearchNewsEvents.CategoryChecked -> {
                val newMap = _state.value.mapCategories.mapValues {
                    if (it.key == event.category) {
                        !it.value
                    } else {
                        it.value
                    }
                }
                _state.tryEmit(
                    _state.value.copy(mapCategories = newMap)
                )
            }

            is SearchNewsEvents.AllCategoriesChecked -> {
                val newMap = _state.value.mapCategories.mapValues { event.isChecked }
                _state.tryEmit(_state.value.copy(mapCategories = newMap))
            }

            is SearchNewsEvents.FilterButton -> Unit//open BottomSheet

            is SearchNewsEvents.BackButton -> navigation.back()

            is SearchNewsEvents.OpenNews -> navigation.navigateTo(
                NewsScreens.OneNewsScreen(event.news)
            )
        }
    }

    private suspend fun setNewsByFilter() {
        val categories = _state.value.mapCategories.filter { it.value }.keys.toList()
        val response = getNewsByParamsUseCase(
            categories = categories,
            text = _state.value.searchingText
        )

        when (response) {
            is ActionResult.Success -> _state.tryEmit(
                _state.value.copy(
                    findingNews = response.result
                )
            )
            is ActionResult.Fail -> _errorState.tryEmit(response.failure)
        }
    }

}
