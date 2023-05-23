package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsToolbar(viewModel: NewsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    Row (
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        SearchNewsEditText(
            text = state.value.searchingNews,
            onTextChange = { viewModel.onEvent(NewsListEvents.SetNewsTitle(it)) }
        )
        FilterButton(
            onClick = { viewModel.onEvent(NewsListEvents.FilterButton) }
        )
    }
}

@Composable
fun SearchNewsEditText(
    text: String,
    onTextChange: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.73

    OutlinedTextField(
        modifier = Modifier.width(width.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = { Text(text = TITLE_SEARCH_TEXT_FIELD)}
    )
}

@Composable
fun FilterButton(onClick: () -> Unit) {
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
            onClick = { onClick() },
            imageId = R.drawable.ic_filter,
            modifier = Modifier
                .size(width.dp)
        )
    }
}

const val TITLE_SEARCH_TEXT_FIELD = "Поиск"
