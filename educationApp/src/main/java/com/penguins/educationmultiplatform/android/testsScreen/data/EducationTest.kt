package com.penguins.educationmultiplatform.android.testsScreen.data

import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategory
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestDifficulty

sealed class EducationTest(
    val category: InterestCategory,
    val difficulty: TestDifficulty,
    val question: String,
    val score: Int,
    val correctMessage: String,
    val wrongMessage: String
) {
    class TextTest(
        category: InterestCategory,
        difficulty: TestDifficulty,
        question: String,
        score: Int,
        correctMessage: String,
        wrongMessage: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(category, difficulty, question, score, correctMessage, wrongMessage)

    class MusicTest(
        category: InterestCategory,
        difficulty: TestDifficulty,
        question: String,
        score: Int,
        correctMessage: String,
        wrongMessage: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(category, difficulty, question, score, correctMessage, wrongMessage)
}