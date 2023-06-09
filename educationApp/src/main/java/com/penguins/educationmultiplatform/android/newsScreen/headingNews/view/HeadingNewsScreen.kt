package com.penguins.educationmultiplatform.android.newsScreen.headingNews.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.toolbar.CategoryToolbar
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.components.HeadingList
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.data.HeadingNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.viewModel.HeadingNewsViewModel
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import org.koin.androidx.compose.koinViewModel

@Composable
fun HeadingNewsScreen(
    categoryTitle: String? = null,
    heading: String? = null,
    viewModel: HeadingNewsViewModel = koinViewModel()
) {
    viewModel.onEvent(
        HeadingNewsEvents.SetTitleAndNews(
            category = categoryTitle,
            title = heading
        )
    )
    val state = viewModel.state.collectAsState()
    val category = state.value.category

    Column(
        modifier = Modifier
            .background(brush = category?.gradientBackground ?: gradientBackground)
            .fillMaxHeight()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 64.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CategoryToolbar(
                    title = state.value.title ?: EMPTY_STRING,
                    onBackClick = { viewModel.onEvent(HeadingNewsEvents.BackButton) },
                    onSearchClick = { viewModel.onEvent(HeadingNewsEvents.SearchButton) }
                )
                HeadingList(
                    news = state.value.news,
                    onClickNews = { news -> viewModel.onEvent(HeadingNewsEvents.OpenNews(news)) }
                )
            }
        }
    }
}