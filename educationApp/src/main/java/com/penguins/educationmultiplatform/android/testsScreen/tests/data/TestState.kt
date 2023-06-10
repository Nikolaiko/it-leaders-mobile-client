package com.penguins.educationmultiplatform.android.testsScreen.tests.data

data class TestState(
    val firstButtonState: AnswerButtonState = AnswerButtonState.NotSelected,
    val secondButtonState: AnswerButtonState = AnswerButtonState.NotSelected,
    val thirdButtonState: AnswerButtonState = AnswerButtonState.NotSelected,
    val fourthButtonState: AnswerButtonState = AnswerButtonState.NotSelected,
    val showFact: Boolean = false,
    val showCheck: Boolean = false,
    val confirmedAnswer: Boolean = false
) {
    companion object {
        fun default() = TestState()

        fun selectedCorrectAnswer(index: Int) = when(index) {
            0 -> TestState(firstButtonState = AnswerButtonState.Correct)
            1 -> TestState(secondButtonState = AnswerButtonState.Correct)
            2 -> TestState(thirdButtonState = AnswerButtonState.Correct)
            3 -> TestState(fourthButtonState = AnswerButtonState.Correct)
            else -> default()
        }
    }
}
