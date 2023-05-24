package com.penguins.educationmultiplatform.android.mapScreen.data

data class YandexMapUiState(
    val schools: List<SchoolDataUi> = emptyList(),
    val filters:SchoolTypeFilter = SchoolTypeFilter(),
    val loading: Boolean = false,
    val error: String = ""
)
