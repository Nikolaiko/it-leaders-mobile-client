package com.penguins.educationmultiplatform.android.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primaryBlue = Color(0xFF0076DE)
val lightBlue = Color(0xFFB9D9FF)
val primaryGray = Color(0xFF7C7C7B)
val primaryWhite = Color(0xFFFFFFFF)
val educationGreenColor = Color(0xFFB4C95E)
val primaryGreen = Color(0xFF9AAE4F)

val textGrayColor = Color(0xFF7C7C7B)

val gradientSplashScreen = Brush.verticalGradient(
    0.0f to primaryBlue,
    0.5f to lightBlue
)
val gradientBackground = Brush.linearGradient(
    0.0f to Color(0xFFB4C95E),
    0.5f to Color(0xFFFFFFFF)
)

val allNewsBackgroundColor = Color(0xFFAED7F1)
val artBackgroundColor = Color(0xFFBA768F)
val danceBackgroundColor = Color(0xFF81AE7B)
val musicBackgroundColor = Color(0xFFB4C95E)
val theatreBackgroundColor = Color(0xFF8E74A8)

val allNewsGradientBackground = Brush.linearGradient(
    0.0f to allNewsBackgroundColor,
    0.5f to Color(0xFFFFFFFF)
)
val artGradientBackground = Brush.linearGradient(
    0.0f to artBackgroundColor,
    0.5f to Color(0xFFFFFFFF)
)
val danceGradientBackground = Brush.linearGradient(
    0.0f to danceBackgroundColor,
    0.5f to Color(0xFFFFFFFF)
)
val musicGradientBackground = Brush.linearGradient(
    0.0f to musicBackgroundColor,
    0.5f to Color(0xFFFFFFFF)
)
val theatreGradientBackground = Brush.linearGradient(
    0.0f to theatreBackgroundColor,
    0.5f to Color(0xFFFFFFFF)
)
