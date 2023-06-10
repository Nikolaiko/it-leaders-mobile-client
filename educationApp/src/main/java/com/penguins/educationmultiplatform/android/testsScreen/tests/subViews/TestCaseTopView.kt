package com.penguins.educationmultiplatform.android.testsScreen.tests.subViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategory
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestDifficulty
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral0Size16Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral800Size20Weight700Style
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.secondary400Size20Weight700Style

@Composable
fun TestCaseTopView(
    modifier: Modifier = Modifier,
    score: Int,
    category: InterestCategory,
    difficulty: TestDifficulty
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        cornerRadius = CornerRadius(30f, 30f),
                        color = primary500
                    )
                }
                .padding(horizontal = 10.dp)
                .padding(vertical = 4.dp),
            text = category.name,
            style = neutral0Size16Weight700Style
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = score.toString(),
                    style = neutral800Size20Weight700Style
                )
                Image(
                    painter = painterResource(id = R.drawable.png_money),
                    contentDescription = ""
                )
            }
            Text(
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            cornerRadius = CornerRadius(30f,30f),
                            color = neutral0
                        )
                    }
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 4.dp),
                text = difficulty.name,
                style = secondary400Size20Weight700Style
            )
        }
    }
}

@Preview
@Composable
fun TestCaseTopViewPreview() {
    MyApplicationTheme {
        Surface {
            TestCaseTopView(
                score = 1324,
                category = InterestCategory.music,
                difficulty = TestDifficulty.Lite
            )
        }
    }
}