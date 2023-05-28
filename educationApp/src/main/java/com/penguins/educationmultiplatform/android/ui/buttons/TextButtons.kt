package com.penguins.educationmultiplatform.android.ui.buttons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.material.TextButton as MaterialTextButton

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    MaterialTextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            fontWeight = fontWeight,
            color = color,
            fontSize = fontSize
        )
    }
}