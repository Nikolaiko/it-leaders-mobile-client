package com.penguins.educationmultiplatform.android.profileScreen.components.mapper

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

fun Category.toChipsText() = when(this) {
    Category.ART -> "Изобразительное искусство"
    Category.DANCE -> "Танцы"
    Category.MUSIC -> "Музыка"
    Category.THEATRE -> "Театр"
}
