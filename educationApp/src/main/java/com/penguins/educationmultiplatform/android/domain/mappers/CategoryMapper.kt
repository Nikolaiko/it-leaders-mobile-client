package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.data.model.dto.news.CategoryParam
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category


fun getCategory(category: String?) = when (category) {
    CategoryParam.ART.title -> Category.ART
    CategoryParam.MUSIC.title -> Category.MUSIC
    CategoryParam.DANCE.title -> Category.DANCE
    CategoryParam.THEATRE.title -> Category.THEATRE
    else -> null
}
