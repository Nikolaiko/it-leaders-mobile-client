package com.model.dto.user

import com.model.dto.interests.InterestsCategoryDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserDataDTO(
    val id: Long,
    val email: String,
    val name: String,
    val birthDate: String?,
    val avatarUrl: String?,
    val interests: List<InterestsCategoryDTO>
)
