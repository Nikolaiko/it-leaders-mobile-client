package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data.ProfileTabsEvents
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data.ProfileTabsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileTabsViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProfileTabsState())
    val state = _state.asStateFlow()

    fun onEvent(events: ProfileTabsEvents) {
        when (events) {
            is ProfileTabsEvents.ClickTab -> {
                _state.tryEmit(
                    _state.value.copy(
                        selectedTab = events.selectedTab
                    )
                )
            }
            ProfileTabsEvents.GetCourses -> Unit
            ProfileTabsEvents.GetFriends -> Unit
        }
    }
}
