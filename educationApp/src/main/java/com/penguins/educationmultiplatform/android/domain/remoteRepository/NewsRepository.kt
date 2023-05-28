package com.penguins.educationmultiplatform.android.domain.remoteRepository

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError

interface NewsRepository {
    suspend fun getNewsByCategory(category: String): ActionResult<NewsListResponse, AppError>
}
