package com.penguins.educationmultiplatform.android.profileScreen.data.model

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

sealed class ProfileEvent {
    object LogOut : ProfileEvent()
    object OpenMenu : ProfileEvent()
    object CloseMenu : ProfileEvent()
    object ChangeImage : ProfileEvent()
    class ClickCategory(val category: Category) : ProfileEvent()
}