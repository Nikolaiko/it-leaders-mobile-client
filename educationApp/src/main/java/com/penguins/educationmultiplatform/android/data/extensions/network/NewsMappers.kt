package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.news.NewsResponseDTO
import com.model.dto.news.NewsResponseListDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsResponse

fun NewsResponseListDTO.toNewsListResponse() = NewsListResponse(
    news = news.map { it.toNewsResponse() }
)

fun NewsResponseDTO.toNewsResponse() = NewsResponse(
    id, title, info, heading, category, imageUrl
)