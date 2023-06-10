package com.penguins.educationmultiplatform.android.profileScreen.data.debug

import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.profileScreen.data.model.User

fun getRatingUsers() = listOf(
    User(
        fullName = "Андрей Иванов",
        rating = 1674,
        imageId = R.drawable.debug_user_1
    ),
    User(
        fullName = "Кирилл Сергеев",
        rating = 174,
        imageId = R.drawable.debug_user_3
    ),
    User(
        fullName = "Ильвина Гуракова",
        rating = 56,
        imageId = R.drawable.debug_user_6
    ),
    User(
        fullName = "Антон Колесников",
        rating = 2,
        imageId = R.drawable.debug_user_4
    )
)

fun getUsersForInvitation() = listOf(
    User(
        fullName = "Анна Иванова",
        rating = 1674,
        imageId = R.drawable.debug_user_2
    ),
    User(
        fullName = "Дмитрий Пучинников",
        rating = 174,
        imageId = R.drawable.debug_user_5
    )
)