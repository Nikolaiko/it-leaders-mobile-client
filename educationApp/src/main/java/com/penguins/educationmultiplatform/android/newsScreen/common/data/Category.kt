package com.penguins.educationmultiplatform.android.newsScreen.common.data

import androidx.compose.ui.graphics.Brush
import com.penguins.educationmultiplatform.android.ui.artGradientBackground
import com.penguins.educationmultiplatform.android.ui.danceGradientBackground
import com.penguins.educationmultiplatform.android.ui.musicGradientBackground
import com.penguins.educationmultiplatform.android.ui.theatreGradientBackground

enum class Category(
    val title: String,
    val background: Brush
) {
    ART(
        title = "Художественная школа",
        background = artGradientBackground
    ),
    DANCE(
        title = "Танцевальная школа",
        background = danceGradientBackground
    ),
    MUSIC(
        title = "Музыкальная школа",
        background = musicGradientBackground
    ),
    THEATRE(
        title = "Театральная школа",
        background = theatreGradientBackground
    )
}
