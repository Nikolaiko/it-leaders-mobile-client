package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.interests.InterestsCategoryDTO
import com.model.dto.interests.InterestsListDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategory

fun InterestsCategoryDTO.toInterestCategory() = when(this) {
    InterestsCategoryDTO.music -> InterestCategory.music
    InterestsCategoryDTO.art -> InterestCategory.art
    InterestsCategoryDTO.theater -> InterestCategory.theatre
    InterestsCategoryDTO.choreography -> InterestCategory.choreography
}

fun InterestCategory.toDTO() = when(this) {
    InterestCategory.music -> InterestsCategoryDTO.music
    InterestCategory.art -> InterestsCategoryDTO.art
    InterestCategory.theatre -> InterestsCategoryDTO.theater
    InterestCategory.choreography -> InterestsCategoryDTO.choreography
}

fun InterestCategoriesList.toInterestsCategoriesListDTO(): InterestsListDTO {
    return InterestsListDTO(
        interests = interests.map { it.toDTO() }
    )
}