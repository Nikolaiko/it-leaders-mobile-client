package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model

import android.net.Uri

data class VideoLesson(
    val name:String,
    val description:String,
    val nameTeacher:String,
    val video:Uri
)