package com.penguins.educationmultiplatform.android.data.model

import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategory
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.data.TextAnswerVariant
import com.penguins.educationmultiplatform.android.testsScreen.tests.data.TestDifficulty

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
    InterestCategory.music,
    TestDifficulty.Lite,
    "Чем занимаются участники хора?",
    1,
    "Поют. Хор – певческий коллектив, исполняющий вокальную музыку",
    "Поют",
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
    InterestCategory.music,
    TestDifficulty.Lite,
    "Кто сочиняет музыку?",
    1,
    "Композитор – автор, создатель музыкальных произведений.",
    "Композитор",
    listOf(text1Answer11, text1Answer12, text1Answer13, text1Answer14)
)

val text1Answer15 = TextAnswerVariant(
    text = "4",
    correct = true
)
val text1Answer16 = TextAnswerVariant(
    text = "5",
    correct = false
)
val text1Answer17 = TextAnswerVariant(
    text = "6",
    correct = false
)
val text1Answer18 = TextAnswerVariant(
    text = "8",
    correct = false
)
val textQuestion3 = EducationTest.TextTest(
    InterestCategory.music,
    TestDifficulty.Medium,
    "Сколько струн на виолончели?",
    2,
    "Виолончель – струнный смычковый музыкальный инструмент, который имеет 4 струны.",
    "4",
    listOf(text1Answer15, text1Answer16, text1Answer17, text1Answer18)
)

val text1Answer19 = TextAnswerVariant(
    text = "Трубач",
    correct = false
)
val text1Answer20 = TextAnswerVariant(
    text = "Композитор",
    correct = false
)
val text1Answer21 = TextAnswerVariant(
    text = "Скрипач",
    correct = false
)
val text1Answer22 = TextAnswerVariant(
    text = "Пианист",
    correct = true
)
val textQuestion4 = EducationTest.TextTest(
    InterestCategory.music,
    TestDifficulty.Medium,
    "Денис Мацуев –...",
    2,
    "Денис Мацуев – российский пианист, Народный артист РФ, победитель XI Международного конкурса имени П.И. Чайковского.",
    "Пианист",
    listOf(text1Answer19, text1Answer20, text1Answer21, text1Answer22)
)

val text1Answer23 = TextAnswerVariant(
    text = "Гармонь",
    correct = false
)
val text1Answer24 = TextAnswerVariant(
    text = "Аккордеон",
    correct = true
)
val text1Answer25 = TextAnswerVariant(
    text = "Баян",
    correct = false
)
val text1Answer26 = TextAnswerVariant(
    text = "Бандонеон",
    correct = false
)
val textQuestion5 = EducationTest.TextTest(
    InterestCategory.music,
    TestDifficulty.Medium,
    "Как называется инструмент, в котором одна из клавиатур напоминает клавиатуру фортепиано?",
    2,
    "Аккордеон – музыкальный инструмент, в котором  правая клавиатура фортепианного типа, то есть, напоминает клавиатуру фортепиано.",
    "Аккордеон",
    listOf(text1Answer23, text1Answer24, text1Answer25, text1Answer26)
)

val text1Answer27 = TextAnswerVariant(
    text = "Балет",
    correct = true
)
val text1Answer28 = TextAnswerVariant(
    text = "Опера",
    correct = false
)
val text1Answer29 = TextAnswerVariant(
    text = "Симфония",
    correct = false
)
val text1Answer30 = TextAnswerVariant(
    text = "Соната",
    correct = false
)
val textQuestion6 = EducationTest.TextTest(
    InterestCategory.music,
    TestDifficulty.Hard,
    "Как называется музыкальный спектакль, содержание которого воплощается через музыку и танец?",
    4,
    "Балет – вид сценического искусства, содержание которого выражается в музыкально-хореографических образах.",
    "Балет",
    listOf(text1Answer27, text1Answer28, text1Answer29, text1Answer30)
)

val text1Answer31 = TextAnswerVariant(
    text = "Балалайка, гитара, гобой, виолончель",
    correct = false
)
val text1Answer32 = TextAnswerVariant(
    text = "Контрабас, виолончель, тромбон, укулеле",
    correct = false
)
val text1Answer33 = TextAnswerVariant(
    text = "Скрипка, контрабас, домра, арфа",
    correct = true
)
val text1Answer34 = TextAnswerVariant(
    text = "Арфа, скрипка, домра, кларнет",
    correct = false
)
val textQuestion7 = EducationTest.TextTest(
    InterestCategory.music,
    TestDifficulty.Hard,
    "Выберите вариант ответа, где перечислены струнные музыкальные инструменты.",
    4,
    "Скрипка, контрабас, домра, арфа, балалайка, гитара, виолончель, укулеле – струнные музыкальные инструменты. Гобой, тромбон, кларнет – духовые музыкальные инструменты.",
    "Скрипка, контрабас, домра, арфа",
    listOf(text1Answer31, text1Answer32, text1Answer33, text1Answer34)
)