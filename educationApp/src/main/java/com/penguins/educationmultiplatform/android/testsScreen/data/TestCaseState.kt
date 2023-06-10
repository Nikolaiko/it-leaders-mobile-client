package com.penguins.educationmultiplatform.android.testsScreen.data

import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestState

data class TestCaseState(
    val currentTest: EducationTest?,
    val currentTestState: TestState,
    val userScore: Int
)
