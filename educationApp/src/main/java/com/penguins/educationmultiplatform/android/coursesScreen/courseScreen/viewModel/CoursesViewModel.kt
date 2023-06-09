package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.CoursesUiEvents
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.CoursesUiState
import com.penguins.educationmultiplatform.android.domain.useCases.GetVideoCoursesUseCase
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolTypeFilter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val FIREBASE_STORAGE = "gs://education-3c7ef.appspot.com/"
const val FIREBASE_URL ="https://firebasestorage.googleapis.com/v0/b/lost-pets-e8cae.appspot.com/o/"


class CoursesViewModel(
    val getVideoCoursesUseCase: GetVideoCoursesUseCase,
) : ViewModel() {

    val uri = mutableStateOf<Uri?>(null)
    private val _search = MutableStateFlow("")
    private val _filter = MutableStateFlow(SchoolTypeFilter())
    private val _state = MutableStateFlow(CoursesUiState())
    val state = combine(_search, _filter, _state) { search, filter, state ->
        if (search.isBlank()) {
            state.copy(
                filters = filter,
                search = "",
                courses = state.courses.filter { it.doesMatchFilter(filter) })
        } else
            state.copy(filters = filter, search = _search.value, courses = state.courses.filter {
                it.doesMatchSearchQuery(search) && it.doesMatchFilter(
                    filter
                )
            })
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CoursesUiState())

    init {

        getVideoCoursesUseCase.invoke().onEach {
            _state.emit(_state.value.copy(courses = it ))
        }.launchIn(viewModelScope)
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun onEvent(event: CoursesUiEvents) {
        when (event) {
            CoursesUiEvents.SetMusicFilter -> {
                _filter.tryEmit(_filter.value.copy(musicalFilter = !_filter.value.musicalFilter))
            }
            CoursesUiEvents.SetAllFilter -> {
                if (_filter.value == SchoolTypeFilter())
                    _filter.tryEmit(SchoolTypeFilter(false, false, false, false))
                else
                    _filter.tryEmit(SchoolTypeFilter(true, true, true, true))
            }
            CoursesUiEvents.SetArtistFilter -> {
                _filter.tryEmit(_filter.value.copy(artistFilter = !_filter.value.artistFilter))
            }
            CoursesUiEvents.SetDancingFilter -> {
                _filter.tryEmit(_filter.value.copy(dancingFilter = !_filter.value.dancingFilter))
            }
            CoursesUiEvents.SetTheaterFilter -> {
                _filter.tryEmit(_filter.value.copy(theatricalFilter = !_filter.value.theatricalFilter))
            }
            is CoursesUiEvents.SetSearchField -> {
                viewModelScope.launch {
                    _search.emit(event.text)
                }
            }
        }
    }
}