package com.penguins.educationmultiplatform.android.domain.usecases

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.news.CategoryParam
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.mappers.toCategoryNewsList
import com.penguins.educationmultiplatform.android.domain.remoteRepository.NewsRepository
import com.penguins.educationmultiplatform.android.newsScreen.common.data.CategoryNewsList

class GetNewsListUseCase(
    private val network: NewsRepository
) {

    suspend operator fun invoke(): ActionResult<List<CategoryNewsList>, AppError> {
        val list = mutableListOf<CategoryNewsList>()

        CategoryParam.values().forEach {
            when (val response = network.getNewsByCategory(it.title)) {
                is ActionResult.Success -> list.add(response.result.news.toCategoryNewsList())
                is ActionResult.Fail -> return response
            }
        }
        return ActionResult.Success(list)
    }

}
