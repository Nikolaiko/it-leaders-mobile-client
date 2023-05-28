package com.penguins.educationmultiplatform.android.testsScreen.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.ui.neutral0Size17Weight700Style
import com.penguins.educationmultiplatform.android.ui.secondary400

@Composable
fun TextAnswerView(
    modifier: Modifier = Modifier,
    text: String,
    callback: VoidCallback = {}
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondary400,
        ),
        onClick = callback
    ) {
        Text(
            text = text,
            style = neutral0Size17Weight700Style,
            textAlign = TextAlign.Center
        )
    }
}