package com.penguins.educationmultiplatform.android.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.news.components.cards.CategoryCardTitle
import com.penguins.educationmultiplatform.android.news.components.cards.DividerAndTextButton
import com.penguins.educationmultiplatform.android.news.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.news.debug.News
import com.penguins.educationmultiplatform.android.news.debug.listOfNews

@Composable
fun NewsList() {
    Column {
        NewsToolbar()
        CategoriesList()
    }
}

@Composable
fun CategoriesList() {
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    ) {
        CategoryCard(listOfNews().first())
//        NewsList(items = listOf("item1", "item2"))
    }
}

@Composable
fun CategoryCard(news: News) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CategoryCardTitleRow(news.category ?: EMPTY_STRING)
        CategoryCardNewsList(news)
    }
}

@Composable
fun CategoryCardTitleRow(
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        CategoryCardTitle(title)
        DividerAndTextButton()
    }
}

@Composable
fun CategoryCardNewsList(news: News) {
    NewsCard(news = news)
//    NewsCategoryList(
//        items = news,
//        modifier = Modifier
//            .padding(top = 32.dp)
//    )
}

@Composable
fun NewsCard(news: News) {
    Card(modifier = Modifier) {
        Box(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.CenterStart,
        ) {
            Image(
                painter = painterResource(id = news.imageId ?: DEFAULT_IMAGE_NEWS),
                contentDescription = IMAGE_CARD_DESCRIPTION,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
            )
            Image(
                painter = painterResource(id = R.drawable.ic_news_background),
                contentDescription = IMAGE_CARD_DESCRIPTION,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .alpha(0.7f)
                            .background(
                                Color.White,
                                RoundedCornerShape(25.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = news.heading ?: EMPTY_STRING,
                            color = Color.DarkGray
                        )
                    }
                    Text(
                        text = news.title ?: EMPTY_STRING,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

const val IMAGE_CARD_DESCRIPTION = "Image of news."
const val DEFAULT_IMAGE_NEWS = R.drawable.png_debug_news
const val EMPTY_STRING = ""

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsList()
}
