package com.penguins.educationmultiplatform.android.data.model.dto.profile

import kotlinx.serialization.Serializable

@Serializable
data class InterestCategoriesList(
    val interests: List<InterestCategory>
)
