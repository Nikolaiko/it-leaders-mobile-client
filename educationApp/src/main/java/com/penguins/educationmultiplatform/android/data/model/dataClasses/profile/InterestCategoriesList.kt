package com.penguins.educationmultiplatform.android.data.model.dataClasses.profile

import kotlinx.serialization.Serializable

@Serializable
data class InterestCategoriesList(
    val interests: List<InterestCategory>
)
