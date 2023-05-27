package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts.SearchNewsEditText
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsToolbar(viewModel: NewsListViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    Row (
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        SearchNewsEditText(
            text = state.value.searchingNews,
            onTextChange = {},//{ viewModel.onEvent(NewsListEvents.SetNewsTitle(it)) },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.onEvent(NewsListEvents.OpenSearchNews) }
        )
    }
}
