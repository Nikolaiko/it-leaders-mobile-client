package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.data.model.dto.news.CategoryParam
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

fun getCategoryFromCategoryParam(category: String?) = when (category) {
    CategoryParam.ART.title -> Category.ART
    CategoryParam.MUSIC.title -> Category.MUSIC
    CategoryParam.DANCE.title -> Category.DANCE
    CategoryParam.THEATRE.title -> Category.THEATRE
    else -> null
}

fun getCategoriesParam(categories: List<Category>?): List<String> = when (categories.isNullOrEmpty()) {
    true -> CategoryParam.values().map { it.title }
    false -> categories.mapNotNull {
        getCategoryParam(
            it.title
        )
    }
}

fun getCategoryParam(category: String?) = when (category) {
    Category.ART.title -> CategoryParam.ART.title
    Category.DANCE.title -> CategoryParam.DANCE.title
    Category.MUSIC.title -> CategoryParam.MUSIC.title
    Category.THEATRE.title -> CategoryParam.THEATRE.title
    else -> null
}
