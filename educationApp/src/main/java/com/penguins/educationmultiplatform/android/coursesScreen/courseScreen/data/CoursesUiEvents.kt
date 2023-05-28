package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data


sealed class CoursesUiEvents{

    object SetMusicFilter : CoursesUiEvents()
    object SetArtistFilter : CoursesUiEvents()
    object SetTheaterFilter : CoursesUiEvents()
    object SetDancingFilter : CoursesUiEvents()
    object SetAllFilter : CoursesUiEvents()
    class SetSearchField (val text:String): CoursesUiEvents()
}
