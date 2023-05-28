package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.data.model.dto.news.NewsResponse
import com.penguins.educationmultiplatform.android.newsScreen.common.data.CategoryNewsList
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Heading
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
    heading = heading?.toHeading()
)

fun String.toHeading(): String = when (this) {
    Heading.famousPeople.name -> Heading.famousPeople.title
    Heading.quiz.name -> Heading.quiz.title
    Heading.interview.name -> Heading.interview.title
    else -> Heading.interestingFacts.title
}
