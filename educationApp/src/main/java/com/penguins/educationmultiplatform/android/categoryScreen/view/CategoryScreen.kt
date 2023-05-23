package com.penguins.educationmultiplatform.android.categoryScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryMap
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryNews
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryToolbar

@Composable
fun CategoryScreen() {
    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CategoryToolbar()
                CategoryNews()
            }
            CategoryMap()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 1640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    CategoryScreen()
}

