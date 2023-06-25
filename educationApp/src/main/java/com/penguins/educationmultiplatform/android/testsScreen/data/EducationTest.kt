package com.penguins.educationmultiplatform.android.testsScreen.data

import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategory
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestDifficulty

sealed class EducationTest(
    val category: InterestCategory,
    val difficulty: TestDifficulty,
    val question: String,
    val score: Int,
    val fact: String,
    val correctAnswer: String
) {
    class TextTest(
        category: InterestCategory,
        difficulty: TestDifficulty,
        question: String,
        score: Int,
        fact: String,
        correctAnswer: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(category, difficulty, question, score, fact, correctAnswer)

    class MusicTest(
        category: InterestCategory,
        difficulty: TestDifficulty,
        question: String,
        score: Int,
        fact: String,
        correctAnswer: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(category, difficulty, question, score, fact, correctAnswer)
}