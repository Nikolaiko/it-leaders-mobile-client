package com.penguins.educationmultiplatform.android.newsScreen.common.data

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.penguins.educationmultiplatform.android.ui.artBackgroundColor
import com.penguins.educationmultiplatform.android.ui.artGradientBackground
import com.penguins.educationmultiplatform.android.ui.danceBackgroundColor
import com.penguins.educationmultiplatform.android.ui.danceGradientBackground
import com.penguins.educationmultiplatform.android.ui.musicBackgroundColor
import com.penguins.educationmultiplatform.android.ui.musicGradientBackground
import com.penguins.educationmultiplatform.android.ui.theatreBackgroundColor
import com.penguins.educationmultiplatform.android.ui.theatreGradientBackground

enum class Category(
    val title: String,
    val gradientBackground: Brush,
    val color: Color
) {
    ART(
        title = "Художественная школа",
        gradientBackground = artGradientBackground,
        color = artBackgroundColor
    ),
    DANCE(
        title = "Танцевальная школа",
        gradientBackground = danceGradientBackground,
        color = danceBackgroundColor
    ),
    MUSIC(
        title = "Музыкальная школа",
        gradientBackground = musicGradientBackground,
        color = musicBackgroundColor
    ),
    THEATRE(
        title = "Театральная школа",
        gradientBackground = theatreGradientBackground,
        color = theatreBackgroundColor
    )
}
