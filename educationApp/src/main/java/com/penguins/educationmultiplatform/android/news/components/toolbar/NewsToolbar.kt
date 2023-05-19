package com.penguins.educationmultiplatform.android.news.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.news.TITLE_SEARCH_TEXT_FIELD
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun NewsToolbar() {
    Row (
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        SearchNewsEditText()
        FilterButton()
    }
}

@Composable
fun SearchNewsEditText() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.73

    var text by remember { mutableStateOf(TITLE_SEARCH_TEXT_FIELD) }

    OutlinedTextField(
        modifier = Modifier.width(width.dp),
        value = text,
        onValueChange = { text = it }
    )
}

@Composable
fun FilterButton() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.1

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        ImageButton(
            onClick = {  },
            imageId = R.drawable.ic_filter,
            modifier = Modifier
                .size(width.dp)
        )
    }
}
