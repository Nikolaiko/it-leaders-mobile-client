package com.penguins.educationmultiplatform.android.profileScreen.subViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.ui.neutral700Size18Weight400Style
import com.penguins.educationmultiplatform.android.ui.neutral900Size20Weight700Style

@Composable
fun UserNameAgeView(
    name: String,
    age: Int
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            name,
            style = neutral900Size20Weight700Style
        )
        Text(
            age.toString(),
            style = neutral700Size18Weight400Style
        )
    }
}

@Preview
@Composable
fun UserNameAgeViewPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            UserNameAgeView(name = "Nikolai Baklanov", age = 39)
        }
    }
}