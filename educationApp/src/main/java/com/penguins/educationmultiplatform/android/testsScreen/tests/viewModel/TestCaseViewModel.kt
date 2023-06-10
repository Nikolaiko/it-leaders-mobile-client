package com.penguins.educationmultiplatform.android.testsScreen.tests.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.tests.GetUserScoreUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.tests.UpdateUserScoreUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.tests.GetTestCaseUseCase
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.data.TestCaseState
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.AnswerButtonState
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TestCaseViewModel(
    private val loadTestCaseUseCase: GetTestCaseUseCase,
    private val getUserScoreUseCase: GetUserScoreUseCase,
    private val updateUserScoreUseCase: UpdateUserScoreUseCase
): ViewModel() {
    private var tests = emptyList<EducationTest>()
    private var selectedAnswerIndex = -1
    private var selectedTestIndex = -1

    private var currentState = TestCaseState(
        null,
        TestState.default(),
        getUserScoreUseCase.invoke()
    )

    private val _state = MutableStateFlow(currentState)
    val state = _state.asStateFlow()

    fun loadTestCase() {
        val testCase = loadTestCaseUseCase.invoke(Category.MUSIC)
        tests = testCase.tests
        selectedTestIndex = 0
        resetTest()
    }

    fun firstVariantCallback() {
        selectedAnswerIndex = 0
        val newTestState = currentState.currentTestState.copy(
            firstButtonState = AnswerButtonState.Selected,
            secondButtonState = AnswerButtonState.NotSelected,
            thirdButtonState = AnswerButtonState.NotSelected,
            fourthButtonState = AnswerButtonState.NotSelected,
            showCheck = true
        )
        currentState = currentState.copy(currentTestState = newTestState)
        _state.tryEmit(currentState)
    }

    fun secondVariantCallback() {
        selectedAnswerIndex = 1
        val newTestState = currentState.currentTestState.copy(
            firstButtonState = AnswerButtonState.NotSelected,
            secondButtonState = AnswerButtonState.Selected,
            thirdButtonState = AnswerButtonState.NotSelected,
            fourthButtonState = AnswerButtonState.NotSelected,
            showCheck = true
        )
        currentState = currentState.copy(currentTestState = newTestState)
        _state.tryEmit(currentState)
    }

    fun thirdVariantCallback() {
        selectedAnswerIndex = 2
        val newTestState = currentState.currentTestState.copy(
            firstButtonState = AnswerButtonState.NotSelected,
            secondButtonState = AnswerButtonState.NotSelected,
            thirdButtonState = AnswerButtonState.Selected,
            fourthButtonState = AnswerButtonState.NotSelected,
            showCheck = true
        )
        currentState = currentState.copy(currentTestState = newTestState)
        _state.tryEmit(currentState)
    }

    fun fourthVariantCallback() {
        selectedAnswerIndex = 3
        val newTestState = currentState.currentTestState.copy(
            firstButtonState = AnswerButtonState.NotSelected,
            secondButtonState = AnswerButtonState.NotSelected,
            thirdButtonState = AnswerButtonState.NotSelected,
            fourthButtonState = AnswerButtonState.Selected,
            showCheck = true
        )
        currentState = currentState.copy(currentTestState = newTestState)
        _state.tryEmit(currentState)
    }

    fun checkCallback() {
        if (!currentState.currentTestState.confirmedAnswer) {
            checkCorrectAnswer()
        }
    }

    private fun checkCorrectAnswer() {
        val correctAnswerIndex = when(val currentTest = tests[selectedTestIndex]) {
            is EducationTest.TextTest -> currentTest.answers.indexOfFirst { it.correct }
            is EducationTest.MusicTest -> currentTest.answers.indexOfFirst { it.correct }
        }
        var newTestState = TestState.selectedCorrectAnswer(correctAnswerIndex)
        newTestState = newTestState.copy(
            showCheck = true,
            showFact = true,
            confirmedAnswer = true
        )

        val userSelectedRight = correctAnswerIndex == selectedAnswerIndex
        if (!userSelectedRight) {
            newTestState = when(selectedAnswerIndex) {
                0 -> newTestState.copy(firstButtonState = AnswerButtonState.Wrong)
                1 -> newTestState.copy(secondButtonState = AnswerButtonState.Wrong)
                2 -> newTestState.copy(thirdButtonState = AnswerButtonState.Wrong)
                3 -> newTestState.copy(fourthButtonState = AnswerButtonState.Wrong)
                else -> newTestState
            }
        } else {
            currentState = currentState.copy(
                userScore = updateUserScoreUseCase.invoke(1)
            )
        }
        currentState = currentState.copy(currentTestState = newTestState)
        _state.tryEmit(currentState)
    }

    fun moveToNextTest() {
        selectedTestIndex += 1
        if (selectedTestIndex >= tests.size) {
            selectedTestIndex = 0
        }
        resetTest()
    }

    private fun resetTest() {
        currentState = currentState.copy(
            currentTest = tests[selectedTestIndex],
            currentTestState = TestState.default()
        )
        _state.tryEmit(currentState)
    }
}