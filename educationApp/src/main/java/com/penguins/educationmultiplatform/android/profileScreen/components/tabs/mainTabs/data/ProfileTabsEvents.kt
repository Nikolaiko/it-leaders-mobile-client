package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data

sealed class ProfileTabsEvents {
    class ClickTab(val selectedTab: ProfileTabsEnum) : ProfileTabsEvents()
    object GetCourses : ProfileTabsEvents()
    object GetFriends : ProfileTabsEvents()
}
