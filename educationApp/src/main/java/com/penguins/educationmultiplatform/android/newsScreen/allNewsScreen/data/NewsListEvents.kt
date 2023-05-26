package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class NewsListEvents {
    class SetNewsTitle(val titleNews: String): NewsListEvents()
    class SetCategoryList(val list: List<Pair<String, List<News>>>) : NewsListEvents()
    object FilterButton : NewsListEvents()
    class OpenCategory(val category: String) : NewsListEvents()
    class OpenNews(val news: News) : NewsListEvents()
}

