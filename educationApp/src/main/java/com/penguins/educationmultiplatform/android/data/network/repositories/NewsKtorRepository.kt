package com.penguins.educationmultiplatform.android.data.network.repositories

import com.model.ActionResult
import com.penguins.educationmultiplatform.android.data.extensions.network.toAppError
import com.penguins.educationmultiplatform.android.data.extensions.network.toNewsListResponse
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.services.network.KtorNetworkLayer
import com.services.storage.TokenStorage

class NewsKtorRepository(
    private val ktorLayer: KtorNetworkLayer
): NewsRepository {
    override suspend fun getNewsByCategory(
        category: String
    ): AppActionResult<NewsListResponse, AppError> {
        val response = ktorLayer.getNewsByCategory(category)
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toNewsListResponse())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }
}