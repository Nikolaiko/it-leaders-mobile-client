package com.penguins.educationmultiplatform.android.profileScreen.data.model

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

data class ProfileScreenState(
    val userName: String = "",
    val age: String = "",
    val rating: Int = 0,
    val imageUrl: String? = null,
    val categories: Map<Category, Boolean> = Category.values().associateWith { false }
)
