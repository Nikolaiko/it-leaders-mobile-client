package com.penguins.educationmultiplatform.android.mapScreen.viewModel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.domain.location.LocationTracker
import com.penguins.educationmultiplatform.android.domain.useCases.GetSchoolsFromRepository
import com.penguins.educationmultiplatform.android.mapScreen.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class YandexMapViewModel(
    private val locationTracker:LocationTracker,
    private val getSchoolsFromRepository: GetSchoolsFromRepository
) : ViewModel() {


    private val _filter = MutableStateFlow(SchoolTypeFilter())
    private val _state = MutableStateFlow(YandexMapUiState())
    val state = combine(_filter, _state) { filter, state ->
        state.copy(filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), YandexMapUiState())

    private val _currentLocation = MutableSharedFlow<Location?>(1)
    val currentLocation = _currentLocation.asSharedFlow()
    init {
        viewModelScope.launch {
            getSchoolsFromRepository.invoke().collect{
                _state.tryEmit(_state.value.copy(schools = it))
            }
        }

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
            YandexMapEvents.GetCurrentLocation -> {
                viewModelScope.launch {
                    _currentLocation.tryEmit(locationTracker.getCurrentLocation())
                }
            }
        }
    }
}