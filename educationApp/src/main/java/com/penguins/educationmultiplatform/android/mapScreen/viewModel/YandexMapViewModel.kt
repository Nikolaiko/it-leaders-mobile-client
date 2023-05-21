package com.penguins.educationmultiplatform.android.mapScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenUiState
import com.penguins.educationmultiplatform.android.mapScreen.data.*
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.*

class YandexMapViewModel : ViewModel() {


    private val _filter = MutableStateFlow(SchoolTypeFilter())


    private val _state = MutableStateFlow(YandexMapUiState())
    val state = combine(_filter, _state) { filter, state ->
        state.copy(filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), YandexMapUiState())

    init {
        _state.tryEmit(
            YandexMapUiState(
                listOf(
                    SchoolDataUi(
                        id = 1,
                        type = SchoolType.ARTISTIC,
                        name = "test first school",
                        address = "testovaya ylitsa",
                        description = "test test test test test",
                        phoneNumber = "89130200151",
                        email = "test@mail.ru",
                        coords = Point(55.754405, 37.619992)
                    ),
                    SchoolDataUi(
                        id = 2,
                        type = SchoolType.MUSICAL,
                        name = "test second school",
                        address = "testovaya ylitsa 2",
                        description = "test test test test test",
                        phoneNumber = "89130200151",
                        email = "test@mail.ru",
                        coords = Point(55.756988, 37.629407)
                    ),
                    SchoolDataUi(
                        id = 3,
                        type = SchoolType.DANCING,
                        name = "test 3 school",
                        address = "testovaya ylitsa 3",
                        description = "test test test test test",
                        phoneNumber = "89130200151",
                        email = "test@mail.ru",
                        coords = Point(55.75566, 37.613448)
                    ),
                    SchoolDataUi(
                        id = 4,
                        type = SchoolType.THEATRICAL,
                        name = "test first school4",
                        address = "testovaya ylitsa 4",
                        description = "test test test test test",
                        phoneNumber = "89130200151",
                        email = "test@mail.ru",
                        coords = Point(55.749178, 37.615642)
                    ),
                    SchoolDataUi(
                        id = 5,
                        type = SchoolType.ARTISTIC,
                        name = "test first school6 ",
                        address = "testovaya ylitsa6",
                        description = "test test test test test",
                        phoneNumber = "89130200151",
                        email = "test@mail.ru",
                        coords = Point(55.750878, 37.63159)
                    ),
                )
            )
        )
    }

    fun onEvent(event: YandexMapEvents) {
        when (event) {
            is YandexMapEvents.SelectSchoolMarker -> {
            }
            is YandexMapEvents.SetMusicFilter -> {
                _filter.tryEmit(_filter.value.copy(musicalFilter = !_filter.value.musicalFilter))
            }
            YandexMapEvents.SetAllFilter -> {
                _filter.tryEmit(SchoolTypeFilter(true, true, true, true))
            }
            YandexMapEvents.SetArtistFilter -> {
                _filter.tryEmit(_filter.value.copy(artistFilter = !_filter.value.artistFilter))
            }
            YandexMapEvents.SetDancingFilter -> {
                _filter.tryEmit(_filter.value.copy(dancingFilter = !_filter.value.dancingFilter))
            }
            YandexMapEvents.SetTheaterFilter -> {
                _filter.tryEmit(_filter.value.copy(theatricalFilter = !_filter.value.theatricalFilter))

            }
        }
    }
}