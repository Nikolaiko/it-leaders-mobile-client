package com.penguins.educationmultiplatform.android.testsScreen.tests.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.penguins.educationmultiplatform.android.ui.allNewsGradientBackground
import com.penguins.educationmultiplatform.android.ui.gradientSplashScreen
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style

@Composable
fun TextTestView(
    test: EducationTest.TextTest,
    firstVariantCallback: VoidCallback,
    secondVariantCallback: VoidCallback,
    thirdVariantCallback: VoidCallback,
    fourthVariantCallback: VoidCallback,
    checkCallback: VoidCallback,
    testState: TestState
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val topPadding = maxHeight.times(0.073F)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = topPadding),
                text = test.question,
                style = neutral900Size32Weight700Style,
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 60.dp)
                    .padding(top = 40.dp),
            ) {
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[0].text,
                    state = testState.firstButtonState,
                    callback = firstVariantCallback
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[1].text,
                    state = testState.secondButtonState,
                    callback = secondVariantCallback
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[2].text,
                    state = testState.thirdButtonState,
                    callback = thirdVariantCallback
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[3].text,
                    state = testState.fourthButtonState,
                    callback = fourthVariantCallback
                )
                if (testState.showFact) {
                    Spacer(modifier = Modifier.weight(1.0f))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = test.correctMessage,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.weight(1.0f))
                }
                if (testState.showCheck) {
                    Spacer(modifier = Modifier.weight(1.0f))
                    VioletteButton(
                        enabled = true,
                        onClick = checkCallback,
                        text = when(testState.confirmedAnswer) {
                            true -> stringResource(id = R.string.continue_text)
                            false -> stringResource(id = R.string.check_answer_text)
                        }
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                }
            }
        }
    }
}

@Preview
@Composable
fun TextTestViewWithCheckPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            TextTestView(
                test = textQuestion1,
                {},
                {},
                {},
                {},
                {},
                TestState.default()
            )
        }
    }
}

@Preview
@Composable
fun TextTestViewWithoutCheckPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            TextTestView(
                test = textQuestion1,
                {},
                {},
                {},
                {},
                {},
                TestState.default()
            )
        }
    }
}