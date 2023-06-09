package com.penguins.educationmultiplatform.android.profileScreen.data.model

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

data class ProfileScreenState(
    val userName: String = "",
    val age: String = "",
    val rating: String = "",
    val imageUrl: String? = null,
    val selectedCategories: List<Category> = emptyList()
)
