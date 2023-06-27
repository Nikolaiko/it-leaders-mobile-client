package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsListResponse

fun NewsListResponse.toFilter(
    category: List<String>? = null,
    heading: String? = null,
    text: String? = null
) = this.news
    .filter { heading.isNullOrEmpty() || it.heading?.toHeading() == heading }
    .filter { currentNews ->
        category.isNullOrEmpty() || category.any { it == currentNews.category}
    }
    .filter {
        text.isNullOrEmpty() ||
                (it.title?.contains(text, ignoreCase = true) ?: false) ||
                (it.heading?.contains(text, ignoreCase = true) ?: false) ||
                (it.info?.contains(text, ignoreCase = true) ?: false)
    }