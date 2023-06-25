package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.user.UserDataDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData

fun UserDataDTO.toLocalUserData() = LocalUserData(
    id = id,
    email = email,
    name = name,
    birthDate = birthDate,
    avatarUrl = avatarUrl,
    interests = interests.map { it.toInterestCategory() }
)