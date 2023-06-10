package com.penguins.educationmultiplatform.android.testsScreen.tests.subViews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.MyApplicationTheme

@Composable
fun TestCaseTopView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Mysarf"
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Column {
            Text(text = "1233")
            Text(text = "Lite")
        }
    }
}

@Preview
@Composable
fun TestCaseTopViewPreview() {
    MyApplicationTheme {
        Surface {
            TestCaseTopView()
        }
    }
}