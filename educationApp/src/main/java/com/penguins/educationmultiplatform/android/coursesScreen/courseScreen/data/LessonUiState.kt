package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data

import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoLesson
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType

data class LessonUiState(
    val lessons:List<VideoLesson> = emptyList(),
    val selectedLesson: VideoLesson? = null,
    val nameSchool:String = "",
    val type:SchoolType = SchoolType.MUSICAL,
    val loading: Boolean = false,
    val error: String = ""
)
