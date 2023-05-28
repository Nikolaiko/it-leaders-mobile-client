package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.components.buttons.BackButton
import com.penguins.educationmultiplatform.android.newsScreen.common.components.editTexts.SearchNewsEditText
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchNewsToolbar(
    viewModel: SearchNewsViewModel = koinViewModel(),
    openBottomSheet: () -> Unit = {}
) {
    val state = viewModel.state.collectAsState()

    Row (
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        BackButton(
            onClick = { viewModel.onEvent(SearchNewsEvents.BackButton) }
        )
        SearchEditText(
            modifier = Modifier.weight(1f),
            text = state.value.searchingText,
            onTextChanged = { viewModel.onEvent(SearchNewsEvents.SetSearchingText(it)) },
            onIconClick = { viewModel.onEvent(SearchNewsEvents.ClearSearch) },
            onSearch = { viewModel.onEvent(SearchNewsEvents.SearchButton) }
        )
        FilterButton(
            onClick = openBottomSheet
        )
    }
}

@Composable
private fun SearchEditText(
    modifier: Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onIconClick: () -> Unit,
    onSearch: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 16.dp)
    ) {
        SearchNewsEditText(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            onTextChange = onTextChanged,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Закрыть поиск.",
                    modifier = Modifier.clickable { onIconClick() }
                )
            },
            onSearch = onSearch
        )
    }
}

@Composable
fun FilterButton(onClick: () -> Unit) {

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
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    SearchNewsToolbar()
}
