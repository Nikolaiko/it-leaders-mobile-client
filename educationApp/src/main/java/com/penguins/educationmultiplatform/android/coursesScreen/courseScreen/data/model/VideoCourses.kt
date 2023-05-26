package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model

import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolTypeFilter

data class VideoCourse(
    val name:String,
    val type:SchoolType,
    val listVideo:List<VideoLesson>
){
    fun doesMatchSearchQuery(query:String):Boolean{
        val matchingCombinations = listOf(
            name,
            "${name.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
    fun doesMatchFilter(filter:SchoolTypeFilter):Boolean{
        return when(type){
            SchoolType.DANCING -> filter.dancingFilter
            SchoolType.MUSICAL -> filter.musicalFilter
            SchoolType.ARTISTIC -> filter.artistFilter
            SchoolType.THEATRICAL -> filter.theatricalFilter
        }
    }
}
