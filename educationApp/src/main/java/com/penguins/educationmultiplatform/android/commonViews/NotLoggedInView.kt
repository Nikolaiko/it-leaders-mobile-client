package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.authScreen.components.EducationButton
import com.penguins.educationmultiplatform.android.data.model.VoidCallback

@Composable
fun NotLoggedInView(
    authCallback: VoidCallback
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EducationButton(
            text = "Authorize",
            clickCallback =  authCallback
        )
    }
}

@Preview
@Composable
fun NotLoggedInPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NotLoggedInView(
                authCallback = { }
            )
        }
    }
}