package com.penguins.educationmultiplatform.android.testsScreen.data

sealed class EducationTest(
    val question: String,
    val score: Int,
    val correctMessage: String,
    val wrongMessage: String
) {
    class TextTest(
        question: String,
        score: Int,
        correctMessage: String,
        wrongMessage: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(question, score, correctMessage, wrongMessage)

    class MusicTest(
        question: String,
        score: Int,
        correctMessage: String,
        wrongMessage: String,
        val answers: List<TextAnswerVariant>
    ): EducationTest(question, score, correctMessage, wrongMessage)
}