package com.penguins.educationmultiplatform.android.data.model

import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.data.TextAnswerVariant

val text1Answer1 = TextAnswerVariant(
    text = "Танцуют",
    correct = false
)
val text1Answer2 = TextAnswerVariant(
    text = "Поют",
    correct = true
)
val text1Answer3 = TextAnswerVariant(
    text = "Дирижируют",
    correct = false
)
val text1Answer4 = TextAnswerVariant(
    text = "Играют на музыкальных инструментах",
    correct = false
)
val textQuestion1 = EducationTest.TextTest(
    "Чем занимаются участники хора?",
    1,
    "Поют. Хор – певческий коллектив, исполняющий вокальную музыку",
    "Поют. Хор – певческий коллектив, исполняющий вокальную музыку",
    listOf(text1Answer1, text1Answer2, text1Answer3, text1Answer4)
)

val text1Answer11 = TextAnswerVariant(
    text = "Дирижер",
    correct = false
)
val text1Answer12 = TextAnswerVariant(
    text = "Композитор",
    correct = true
)
val text1Answer13 = TextAnswerVariant(
    text = "Концертмейстер",
    correct = false
)
val text1Answer14 = TextAnswerVariant(
    text = "Вокалист",
    correct = false
)
val textQuestion2 = EducationTest.TextTest(
    "Кто сочиняет музыку?",
    1,
    "Правильный ответ: композитор. Композитор – автор, создатель музыкальных произведений.",
    "Правильный ответ: композитор. Композитор – автор, создатель музыкальных произведений.",
    listOf(text1Answer11, text1Answer12, text1Answer13, text1Answer14)
)