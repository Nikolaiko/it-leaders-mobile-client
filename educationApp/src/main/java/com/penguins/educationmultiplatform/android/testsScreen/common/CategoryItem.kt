package com.penguins.educationmultiplatform.android.testsScreen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral0Size17Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral400
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    name: String,
    background: Color,
    enabled: Boolean = true,
    callback: VoidCallback = {}
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            disabledBackgroundColor = neutral400
        ),
        onClick = callback
    ) {
       Text(
           text = name,
           style = neutral0Size17Weight700Style,
           textAlign = TextAlign.Center
       )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    MyApplicationTheme {
        Surface {
            CategoryItem(
                modifier = Modifier
                    .width(162.dp)
                    .height(162.dp),
                name = "Музыка",
                background = primary500
            )
        }
    }
}