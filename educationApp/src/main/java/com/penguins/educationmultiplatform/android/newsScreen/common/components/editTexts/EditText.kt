package com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchNewsEditText(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder = { Text(text = TITLE_SEARCH_TEXT_FIELD) },
        shape = RoundedCornerShape(15.dp),
        enabled = enabled,
        trailingIcon = trailingIcon
    )
}

const val TITLE_SEARCH_TEXT_FIELD = "Поиск"
