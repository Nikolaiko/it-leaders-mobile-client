package com.penguins.educationmultiplatform.android.domain.useCases.news

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.mappers.getCategoriesParam
import com.penguins.educationmultiplatform.android.domain.mappers.toFilter
import com.penguins.educationmultiplatform.android.domain.mappers.toNewsList
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

class GetNewsByParamsUseCase(
    private val network: NewsRepository
) {

    suspend operator fun invoke(
        categories: List<Category>? = null,
        heading: String? = null,
        text: String? = null
    ): AppActionResult<List<News>, AppError> {
        val categoriesParams = getCategoriesParam(categories)
        val result = mutableListOf<News>()

        categoriesParams.forEach {
            when (val response = network.getNewsByCategory(it)) {
                is AppActionResult.Success -> result.addAll(
                    response.result
                        .toFilter(
                            category = categoriesParams,
                            heading = heading,
                            text = text
                        )
                        .toNewsList()
                )
                is AppActionResult.Fail -> return response
            }
        }
        return AppActionResult.Success(result)
    }

}