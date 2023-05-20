package com.penguins.educationmultiplatform.android.authScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.ui.educationGreenColor
import com.penguins.educationmultiplatform.android.ui.textFieldBackGroundColor
import com.penguins.educationmultiplatform.android.utils.UiText


@Composable
fun FormField(
    modifier: Modifier = Modifier,
    text: String = "",
    placeHolder: String,
    visibleIcon: Boolean = false,
    validationValue: UiText = UiText.EmptyString,
    valueCallback: (String) -> Unit
) {


    val visibleText = remember {
        mutableStateOf(!visibleIcon)
    }

    val visualTransformation =
        if (visibleText.value) VisualTransformation.None else PasswordVisualTransformation()

    val borderColor = when (validationValue) {
        is UiText.EmptyString -> Color.Transparent
        else -> Color.Red
    }

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Card(
            Modifier
                .border(
                    2.dp,
                    borderColor,
                    RoundedCornerShape(8.dp)
                ),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp
        ) {
                TextField(
                    modifier = modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = textFieldBackGroundColor,

                        focusedIndicatorColor = educationGreenColor,
                        unfocusedIndicatorColor = educationGreenColor
                    ),
                    value = text,
                    onValueChange = { valueCallback.invoke(it) },

                    placeholder = { Text(placeHolder) },
                    visualTransformation = visualTransformation
                )

        }
    }
}