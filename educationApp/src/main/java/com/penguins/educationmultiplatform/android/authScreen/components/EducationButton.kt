package com.penguins.educationmultiplatform.android.authScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EducationButton(
    modifier: Modifier,
    text: String? = null,
    image: Int? = null,
    imageSize: Dp = 23.dp,
    imageAfter: Int? = null,
    clickCallback: () -> Unit
) {
    Button(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray
        ), onClick = clickCallback
    ) {
        Row(
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