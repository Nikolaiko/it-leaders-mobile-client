package com.penguins.educationmultiplatform.android.newsScreen.data

import com.penguins.educationmultiplatform.android.R

fun listOfCategories() = listOf<Pair<String, List<News>>>(
    "Музыка" to listOfNews(),
    "Хореография" to listOfNews(),
    "Изобразительное искусство" to listOfNews(),
    "Театр" to listOfNews()
)

fun listOfNews() = listOf<News>(
    News(
        title = "Название новости номер 1",
        imageId = R.drawable.png_debug_news,
        text = "Текст новости текст новости текст новости текст новости текст новости текст новости текст новости текст",
        category = "Музыка",
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
