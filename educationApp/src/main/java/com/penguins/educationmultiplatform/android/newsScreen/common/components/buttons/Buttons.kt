package com.penguins.educationmultiplatform.android.newsScreen.common.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.ic_back
        )
    }
}
