package com.penguins.educationmultiplatform.android.categoryScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R

@Composable
fun CategoryMap() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.png_debug_map),
            contentDescription = "debug map",
            contentScale = ContentScale.FillWidth
        )
    }
}
