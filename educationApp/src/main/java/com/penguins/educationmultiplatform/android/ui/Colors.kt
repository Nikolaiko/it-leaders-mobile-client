package com.penguins.educationmultiplatform.android.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primaryBlue = Color(0xFF0076DE)
val lightBlue = Color(0xFFB9D9FF)
val primaryGray = Color(0xFF7C7C7B)

val textFieldBackGroundColor = Color(0xFFFFFFFF)
val educationGreenColor = Color(0xFFB4C95E)

val gradientSplashScreen = Brush.verticalGradient(
    0.0f to primaryBlue,
    0.5f to lightBlue
)
val gradientBackground = Brush.linearGradient(
    0.0f to Color(0xFFB4C95E),
    0.5f to Color(0xFFFFFFFF)
)
