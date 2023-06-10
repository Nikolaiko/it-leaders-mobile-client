package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data

enum class ProfileTabsEnum(
    val code: Int,
    val title: String
    ) {
    MY_COURSES(
        code = 0,
        title = "Мои курсы"
    ),
    FRIENDS(
        code = 1,
        title = "Рейтинг"
    )
}
