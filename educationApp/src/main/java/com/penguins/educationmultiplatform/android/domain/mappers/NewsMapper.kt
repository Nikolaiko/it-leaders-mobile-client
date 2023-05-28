package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.data.model.dto.news.NewsResponse
import com.penguins.educationmultiplatform.android.newsScreen.common.data.CategoryNewsList
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

fun List<NewsResponse>.toCategoryNewsList(): CategoryNewsList = CategoryNewsList(
    category = getCategoryFromCategoryParam(firstOrNull()?.category),
    news = toNewsList()
)

fun List<NewsResponse>.toNewsList() = this
    .map { it.toNews() }

fun NewsResponse.toNews() = News(
    title = title,
    text = info,
    category = getCategoryFromCategoryParam(category),
    heading = heading
)
