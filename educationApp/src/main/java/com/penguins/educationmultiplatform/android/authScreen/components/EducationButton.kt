package com.penguins.educationmultiplatform.android.authScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.ui.educationButtonStyle
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun EducationButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    image: Int? = null,
    imageSize: Dp = 23.dp,
    imageAfter: Int? = null,
    enabled: Boolean = true,
    clickCallback: () -> Unit
) {
    Button(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(12.dp)),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = primaryGray,
            disabledBackgroundColor = Color.Red
        ), onClick = clickCallback
    ) {
        Row(
            Modifier.padding(18.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            image?.let {
                Image(
                    modifier = Modifier.height(imageSize),
                    painter = painterResource(id = it),
                    contentDescription = "logo_button",
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            text?.let {
                Text(
                    text,
                    style = educationButtonStyle
                )
            }
            imageAfter?.let {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.height(imageSize),
                    painter = painterResource(id = it),
                    contentDescription = "logo_button",
                )
            }
        }
    }
}

@Preview
@Composable
fun EducationButtonPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            EducationButton(
                text = "Test"
            ) { }
        }
    }
}