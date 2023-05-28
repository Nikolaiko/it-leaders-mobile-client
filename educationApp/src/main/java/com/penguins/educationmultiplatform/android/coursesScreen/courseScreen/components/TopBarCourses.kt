package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts.TITLE_SEARCH_TEXT_FIELD
import com.penguins.educationmultiplatform.android.ui.*
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun TopBarCourses(
    text: String,
    onTextChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.85f),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primaryBlack,
                unfocusedBorderColor = primaryBlack,
                cursorColor = primaryBlack
            ),
            placeholder = {
                Column(Modifier.fillMaxSize()) {
                    Text(
                        text = TITLE_SEARCH_TEXT_FIELD,
                        fontWeight = FontWeight.W400,
                        fontFamily = calibri,
                        color = fontPlaceholderColor
                    )
                }
            },
            textStyle = textFieldTextStyle
            )
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.filter_alt__1_,
            modifier = Modifier
                .size(40.dp)
        )
    }
}
