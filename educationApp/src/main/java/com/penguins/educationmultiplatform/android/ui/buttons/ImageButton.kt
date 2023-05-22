package com.penguins.educationmultiplatform.android.ui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    onClickLabel: String = "Clickable image",
    onClick: () -> Unit,
    imageId: Int,
    contentDescription: String = "Image Button.",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier
            .clickable(
                enabled = true,
                onClickLabel = onClickLabel,
                onClick = { onClick() }
            ),
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}
