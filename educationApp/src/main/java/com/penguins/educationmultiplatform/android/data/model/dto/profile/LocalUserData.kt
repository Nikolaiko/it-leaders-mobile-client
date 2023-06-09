package com.penguins.educationmultiplatform.android.data.model.dto.profile

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import kotlinx.serialization.Serializable

@Serializable
data class LocalUserData(
    val id: Long,
    val email: String,
    val name: String,
    val birthDate: String?,
    val avatarUrl: String?,
    val interests: List<Category>
)
