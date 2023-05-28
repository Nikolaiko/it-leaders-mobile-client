package com.penguins.educationmultiplatform.android.newsScreen.common.data

fun getCategory(category: String?) = Category.values()
    .firstOrNull { it.title == category }
