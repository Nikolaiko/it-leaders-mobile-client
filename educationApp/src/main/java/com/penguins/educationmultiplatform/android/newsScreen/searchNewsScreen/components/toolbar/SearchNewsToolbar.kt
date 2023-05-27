package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.components.buttons.BackButton
import com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts.SearchNewsEditText
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun SearchNewsToolbar() {
//    val state = viewModel.state.collectAsState()
    val c = LocalContext.current

    Row (
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        BackButton(
            onClick = {}
        )
        SearchEditText(
            modifier = Modifier.weight(1f)
        )
        FilterButton(
            onClick = {}//{ viewModel.onEvent(NewsListEvents.FilterButton) }
        )
    }
}

@Composable
private fun SearchEditText(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(start = 16.dp)
    ) {
        SearchNewsEditText(
            modifier = Modifier
                .fillMaxWidth(),
            text = "",//state.value.searchingNews,
            onTextChange = {},//{ viewModel.onEvent(NewsListEvents.SetNewsTitle(it)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Закрыть поиск."
                )
            }
        )
    }
}

@Composable
fun FilterButton(onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.1

    Box (
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        ImageButton(
            onClick = { onClick() },
            imageId = R.drawable.ic_filter,
            modifier = Modifier
                .size(width.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    SearchNewsToolbar()
}
