package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.map.CategoryMap
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.newsColumn.CategoryNews
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.toolbar.CategoryToolbar
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryEvents
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryScreen(viewModel: CategoryViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()
    val category = state.value.category

    Column(
        modifier = Modifier
            .background(brush = category?.background ?: gradientBackground)
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
                    title = category?.title ?: EMPTY_STRING,
                    onBackClick = { viewModel.onEvent(CategoryEvents.BackButton) },
                    onSearchClick = { viewModel.onEvent(CategoryEvents.SearchButton) }
                )
                CategoryNews(
                    news = viewModel.state.value.headingNews,
                    lastNews = viewModel.state.value.lastNews ?: News(),
                    onClickNews = { news ->
                        viewModel.onEvent(CategoryEvents.OpenNews(news))
                    },
                    onClickSeeAll = { heading ->
                        viewModel.onEvent(CategoryEvents.OpenNewsList(heading))
                    }
                )
            }
            CategoryMap(state.value.category)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 1440, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    CategoryScreen()
}

