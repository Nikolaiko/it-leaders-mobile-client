package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.ui.neutral0

@Composable
fun NotLoggedInView(
    authCallback: VoidCallback
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(neutral0),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EducationButton(
            text = stringResource(id = R.string.authorize_text),
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