package com.penguins.educationmultiplatform.android.mapScreen.data

sealed class YandexMapEvents{
    class SelectSchoolMarker(val school:SchoolDataUi): YandexMapEvents()
    object SetMusicFilter : YandexMapEvents()
    object SetArtistFilter : YandexMapEvents()
    object SetTheaterFilter : YandexMapEvents()
    object SetDancingFilter : YandexMapEvents()
    object SetAllFilter : YandexMapEvents()


}
