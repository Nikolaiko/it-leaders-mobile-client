package com.penguins.educationmultiplatform.android.domain.network

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError

interface NewsRepository {
    suspend fun getNewsByCategory(category: String): AppActionResult<NewsListResponse, AppError>
}
