package com.penguins.educationmultiplatform.android.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

val educationButtonStyle = TextStyle(
    color = Color.White,
    fontSize = TextUnit(16F, TextUnitType.Sp),
    fontWeight = FontWeight.W700
)
val textInactiveBottomItem = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.W400,
    fontStyle = FontStyle.Normal,
    color = primaryGray,
    fontFamily = calibri,
)
val textActiveBottomItem = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = primaryGreen,
    fontFamily = calibri,
)

val neutral900Size20Weight700Style = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = neutral900,
    fontFamily = calibri,
)

val neutral900Size32Weight700Style = TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = neutral900,
    fontFamily = calibri,
)

val neutral700Size18Weight400Style = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = neutral700,
    fontFamily = calibri,
)

val neutral0Size17Weight700Style = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = neutral0,
    fontFamily = calibri,
)