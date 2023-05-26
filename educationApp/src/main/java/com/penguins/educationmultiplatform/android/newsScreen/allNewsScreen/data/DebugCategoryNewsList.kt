package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data

import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

fun listOfCategories() = listOf<Pair<String, List<News>>>(
    Category.MUSIC.title to listOfNews(),
    Category.DANCE.title to listOfNews(),
    Category.ART.title to listOfNews(),
    Category.THEATRE.title to listOfNews()
)

fun listOfNews() = listOf<News>(
    News(
        title = "Название новости номер 1",
        imageId = R.drawable.png_debug_news,
        text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
        category = Category.MUSIC,
        heading = "Рубрика 1"
    ),
    News(
        title = "Название новости номер 2",
        imageId = R.drawable.png_debug_news,
        text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
        category = null,
        heading = "Рубрика 2"
    ),
    News(
        title = "Название новости номер 3",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = null,
        heading = "Рубрика 3"
    ),
    News(
        title = "Название новости номер 4",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = null,
        heading = "Рубрика 4"
    )
)
