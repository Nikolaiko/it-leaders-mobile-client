package com.penguins.educationmultiplatform.android.domain.usecases.tests

import com.penguins.educationmultiplatform.android.data.model.textQuestion1
import com.penguins.educationmultiplatform.android.data.model.textQuestion2
import com.penguins.educationmultiplatform.android.data.model.textQuestion3
import com.penguins.educationmultiplatform.android.data.model.textQuestion4
import com.penguins.educationmultiplatform.android.data.model.textQuestion5
import com.penguins.educationmultiplatform.android.data.model.textQuestion6
import com.penguins.educationmultiplatform.android.data.model.textQuestion7
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.data.TestCase
import com.penguins.educationmultiplatform.android.testsScreen.data.TextAnswerVariant

class GetTestCaseUseCase {
    fun invoke(category: Category): TestCase {
        return TestCase(
            tests = listOf(
                textQuestion1,
                textQuestion2,
                textQuestion3,
                textQuestion4,
                textQuestion5,
                textQuestion6,
                textQuestion7
            ).shuffled()
        )
    }
}