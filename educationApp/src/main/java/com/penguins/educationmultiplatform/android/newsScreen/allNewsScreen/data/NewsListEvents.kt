package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data

sealed class NewsListEvents {
    class SetNewsTitle(val titleNews: String): NewsListEvents()
    class SetCategoryList(val list: List<Pair<String, List<News>>>) : NewsListEvents()
    object FilterButton : NewsListEvents()
    object OpenCategory : NewsListEvents()
    object OpenNews : NewsListEvents()
}

