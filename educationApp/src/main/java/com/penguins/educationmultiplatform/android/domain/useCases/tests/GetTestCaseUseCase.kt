package com.penguins.educationmultiplatform.android.domain.usecases.tests

import com.penguins.educationmultiplatform.android.data.model.textQuestion1
import com.penguins.educationmultiplatform.android.data.model.textQuestion2
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.data.TestCase
import com.penguins.educationmultiplatform.android.testsScreen.data.TextAnswerVariant

class GetTestCaseUseCase {
    fun invoke(category: Category): TestCase {
        return TestCase(
            tests = listOf(textQuestion1, textQuestion2)
        )
    }
}