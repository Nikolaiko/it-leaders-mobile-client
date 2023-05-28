package com.penguins.educationmultiplatform.android.data.model.consts

import com.vk.api.sdk.auth.VKScope
import java.text.SimpleDateFormat

const val minPasswordLength = 3

val birthDateFormat = SimpleDateFormat("yyyy-MM-dd")
val vkFields = listOf(VKScope.EMAIL)