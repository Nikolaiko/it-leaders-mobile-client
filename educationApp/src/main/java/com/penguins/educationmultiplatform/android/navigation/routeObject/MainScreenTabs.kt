package com.penguins.educationmultiplatform.android.navigation.routeObject

import com.penguins.educationmultiplatform.android.R


const val newsTab = "newsTab"
const val coursesTab = "coursesTab"
const val testsTab = "testsTab"
const val mapTab = "mapTab"
const val profileTab = "profileTab"

sealed class MainScreenTabRoute(val route: String, val title: String, val activeIcon: Int, val inActiveIcon: Int) {
    object NewsTab: MainScreenTabRoute(newsTab, "Новости", R.drawable.ic_news_selected, R.drawable.ic_news_tab)
    object CoursesTab: MainScreenTabRoute(coursesTab, "Курсы", R.drawable.ic_courses_selected, R.drawable.ic_courses_tab)
    object TestsTab: MainScreenTabRoute(testsTab,"Тесты",R.drawable.ic_tests_selected, R.drawable.ic_tests_tab)
    object MapTab: MainScreenTabRoute(mapTab,"Карта",R.drawable.ic_map_selected, R.drawable.ic_map_tab)
    object ProfileTab: MainScreenTabRoute(profileTab, "Профиль", R.drawable.ic_profile_selected, R.drawable.ic_profile_tab)
}
