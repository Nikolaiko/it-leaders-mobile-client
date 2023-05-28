package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data

import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

fun listOfNewByHeading() = listOf(
    "Подзаголовок" to listOf(
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 1"
        ),
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 1"
        )
    ),
    "Подзаголовок" to listOf(
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 1"
        ),
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 1"
        ),
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 3"
        ),
        News(
            title = "Заголовок новости",
            text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
            category = Category.MUSIC,
            heading = "Рубрика 4"
        )
    )
)