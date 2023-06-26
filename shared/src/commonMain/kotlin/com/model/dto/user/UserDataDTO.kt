package com.model.dto.user

import com.model.dto.interests.InterestsCategoryDTO

data class UserDataDTO(
    val id: Long,
    val email: String,
    val name: String,
    val birthDate: String?,
    val avatarUrl: String?,
    val interests: List<InterestsCategoryDTO>
)
