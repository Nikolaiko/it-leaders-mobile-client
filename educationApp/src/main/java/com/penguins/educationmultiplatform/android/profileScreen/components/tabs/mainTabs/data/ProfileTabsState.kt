package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data

import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoCourse
import com.penguins.educationmultiplatform.android.profileScreen.data.model.User

data class ProfileTabsState(
    val selectedTab: ProfileTabsEnum = ProfileTabsEnum.MY_COURSES,
    val myCourses: List<VideoCourse> = emptyList(),
    val friends: List<User> =  emptyList()
)
