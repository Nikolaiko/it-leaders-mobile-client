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
    fontSize = 12.sp,
    fontWeight = FontWeight.W700,
    fontStyle = FontStyle.Normal,
    color = primaryGreen,
    fontFamily = calibri,
)

val textFieldTextStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.W400,
    fontStyle = FontStyle.Normal,
    color = textGrayColor,
    fontFamily = calibri,
)