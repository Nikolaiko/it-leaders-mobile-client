package com.penguins.educationmultiplatform.android.testsScreen.tests.subViews

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.commonViews.VioletteButton
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.data.model.textQuestion1
import com.penguins.educationmultiplatform.android.testsScreen.common.TextAnswerView
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.AnswerButtonState
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestState
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style

@Composable
fun TextTestResultView(
    test: EducationTest.TextTest,
    testState: TestState,
    proceedCallback: VoidCallback
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val topPadding = maxHeight.times(0.073F)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = topPadding),
                text = test.question,
                style = neutral900Size32Weight700Style,
                textAlign = TextAlign.Center
            )
            TextAnswerView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                text = test.correctAnswer,
                state = when(isTestFailed(testState)) {
                    true -> AnswerButtonState.Wrong
                    false -> AnswerButtonState.Correct
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
            ) {
                Text(
                    text = test.fact,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            VioletteButton(
                enabled = true,
                onClick = proceedCallback,
                text = stringResource(id = R.string.continue_text)
            )
            Spacer(modifier = Modifier.weight(1.0f))
        }
    }
}

fun isTestFailed(testState: TestState): Boolean {
    return testState.firstButtonState == AnswerButtonState.Wrong
            || testState.secondButtonState == AnswerButtonState.Wrong
            || testState.thirdButtonState == AnswerButtonState.Wrong
            || testState.fourthButtonState == AnswerButtonState.Wrong
}

@Preview
@Composable
fun TextTestPreview() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            TextTestResultView(
                textQuestion1,
                TestState.default(),
                { }
            )
        }
    }
}