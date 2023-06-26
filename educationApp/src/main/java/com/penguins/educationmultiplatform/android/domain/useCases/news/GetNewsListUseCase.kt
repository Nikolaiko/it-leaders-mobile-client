package com.penguins.educationmultiplatform.android.domain.useCases.news

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.CategoryParam
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.mappers.toCategoryNewsList
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.penguins.educationmultiplatform.android.newsScreen.common.data.CategoryNewsList

class GetNewsListUseCase(
    private val network: NewsRepository
) {

    suspend operator fun invoke(): AppActionResult<List<CategoryNewsList>, AppError> {
        val list = mutableListOf<CategoryNewsList>()

        CategoryParam.values().forEach {
            when (val response = network.getNewsByCategory(it.title)) {
                is AppActionResult.Success -> list.add(response.result.news.toCategoryNewsList())
                is AppActionResult.Fail -> return response
            }
        }
        return AppActionResult.Success(list)
    }
}
