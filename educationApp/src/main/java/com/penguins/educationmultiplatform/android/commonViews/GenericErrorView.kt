package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style

@Composable
fun GenericErrorView(
    buttonCallback: VoidCallback? = null,
    errorText: String = stringResource(id = R.string.generic_error_text)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(neutral0),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            errorText,
            style = neutral900Size32Weight700Style
        )
        if (buttonCallback != null) {
            EducationButton(
                text = stringResource(id = R.string.retry_text),
                clickCallback = buttonCallback
            )
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GenericErrorView(
                buttonCallback = {  }
            )
        }
    }
}