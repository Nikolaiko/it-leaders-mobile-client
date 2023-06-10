package com.penguins.educationmultiplatform.android.testsScreen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.common.cache.Weigher
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.AnswerButtonState
import com.penguins.educationmultiplatform.android.ui.calibri
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral0Size17Weight700Style
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.secondary400
import com.penguins.educationmultiplatform.android.ui.wrongColor

@Composable
fun TextAnswerView(
    state: AnswerButtonState,
    modifier: Modifier = Modifier,
    text: String,
    callback: VoidCallback = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = getAnswerButtonColor(state),
        ),
        onClick = callback
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = getTextColor(state),
                fontWeight = FontWeight.W700,
                fontSize = 17.sp,
                fontFamily = calibri
            ),
            textAlign = TextAlign.Center
        )
    }
}

fun getAnswerButtonColor(state: AnswerButtonState) = when(state) {
    AnswerButtonState.Selected -> secondary400
    AnswerButtonState.NotSelected -> neutral0
    AnswerButtonState.Wrong -> wrongColor
    AnswerButtonState.Correct -> primary500
}

fun getTextColor(state: AnswerButtonState) = when(state) {
    AnswerButtonState.NotSelected -> secondary400
    else -> neutral0
}

@Preview
@Composable
fun TestAnswerButtonPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.background(Color.Red)) {
            TextAnswerView(
                AnswerButtonState.NotSelected,
                text = "Some"
            )
        }
    }
}