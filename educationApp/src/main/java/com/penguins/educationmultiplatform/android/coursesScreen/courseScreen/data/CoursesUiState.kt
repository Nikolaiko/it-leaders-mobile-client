package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data

import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoCourse
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolTypeFilter

data class CoursesUiState(
    val courses: List<VideoCourse> = emptyList(),
    val filters: SchoolTypeFilter = SchoolTypeFilter(),
    val search: String = "",
    val loading: Boolean = false,
    val error: String = ""
)
