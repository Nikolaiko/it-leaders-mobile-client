package com.penguins.educationmultiplatform.android.domain.mappers

import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.consts.birthDateFormat
import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategory
import com.penguins.educationmultiplatform.android.data.model.dto.profile.LocalUserData
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileScreenState
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

fun LocalUserData.toProfileState() = ProfileScreenState(
    userName = name,
    age = "26 лет",//getAge(birthDate),
    rating = 0,
    imageUrl = avatarUrl,
    imageId = R.drawable.debug_user,
    categories = interests.toUserCategories()
)

fun getAge(birthday: String?): String {
    return when (birthday) {
        null -> ""
        else -> {
            val localDate = birthDateFormat.parse(birthday)
                    ?.toInstant()
                    ?.atZone(ZoneId.systemDefault())
                    ?.toLocalDate()

            "Возраст: ${
                Period.between(
                    localDate,
                    LocalDate.now()
                ).years
            }"
        }
    }
}

fun List<InterestCategory>.toUserCategories() = this.associate {
    it.toCategory() to true
}

fun InterestCategory.toCategory() = when (this) {
    InterestCategory.art -> Category.ART
    InterestCategory.choreography -> Category.DANCE
    InterestCategory.music -> Category.MUSIC
    InterestCategory.theater -> Category.THEATRE
}
