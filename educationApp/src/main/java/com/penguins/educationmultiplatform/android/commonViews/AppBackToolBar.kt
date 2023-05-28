package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun AppBackToolbar(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 56.dp, start = 32.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.ic_back
        )
    }
}
