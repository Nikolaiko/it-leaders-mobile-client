package com.penguins.educationmultiplatform.android.news.debug

import com.penguins.educationmultiplatform.android.R

fun listOfNews() = listOf<News>(
    News(
        title = "Название новости номер один",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = "Музыка",
        heading = "Рубрика 1"
    ),
    News(
        title = "Название новости номер два",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = null,
        heading = "Рубрика 2"
    ),
    News(
        title = "Название новости номер три",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = null,
        heading = "Рубрика 3"
    ),
    News(
        title = "Название новости номер четыре",
        imageId = R.drawable.png_debug_news,
        text = null,
        category = null,
        heading = "Рубрика 4"
    )
)
