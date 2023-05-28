package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style

@Composable
fun GenericErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Неизвестная ошибка",
            style = neutral900Size32Weight700Style
        )
    }
}