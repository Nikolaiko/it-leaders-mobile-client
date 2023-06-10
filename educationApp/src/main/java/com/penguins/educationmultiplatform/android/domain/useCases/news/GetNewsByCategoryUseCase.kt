package com.penguins.educationmultiplatform.android.domain.useCases.news

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.mappers.getCategoryParam
import com.penguins.educationmultiplatform.android.domain.mappers.toNewsList
import com.penguins.educationmultiplatform.android.domain.remoteRepository.NewsRepository
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

class GetNewsByCategoryUseCase(
    private val network: NewsRepository
) {

    suspend operator fun invoke(category: String?): ActionResult<List<News>, AppError> {
        val categoryParam = getCategoryParam(category)
        if (category == null) return ActionResult.Fail(AppError.UnknownResponse)
        return when (val response = network.getNewsByCategory(categoryParam!!)) {
            is ActionResult.Success -> ActionResult.Success(response.result.news.toNewsList())
            is ActionResult.Fail -> response
        }
    }

}
