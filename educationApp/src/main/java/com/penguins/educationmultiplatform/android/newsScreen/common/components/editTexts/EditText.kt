package com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.ui.body1RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.textInactiveBottomItem

@Composable
fun SearchNewsEditText(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSearch: (() -> Unit)? = null,
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
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke() }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        textStyle = body1RegularTextStyle
    )
}

const val TITLE_SEARCH_TEXT_FIELD = "Поиск"
