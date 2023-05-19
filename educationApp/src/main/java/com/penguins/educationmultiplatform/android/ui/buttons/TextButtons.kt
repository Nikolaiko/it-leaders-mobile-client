package com.penguins.educationmultiplatform.android.ui.buttons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.TextButton as MaterialTextButton

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    MaterialTextButton(onClick = onClick) {
        Text(text = text)
    }
}