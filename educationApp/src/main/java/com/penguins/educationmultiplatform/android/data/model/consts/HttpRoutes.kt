package com.penguins.educationmultiplatform.android.data.model.consts

object HttpRoutes {
    const val BASE_URL = "http://ovz2.nikbakland.me78p.vps.myjino.ru:49338"
    //const val BASE_URL = "http://10.2.2.2:8080"
    const val REGISTER = "$BASE_URL/auth/register"
    const val REGISTER_VK = "$BASE_URL/auth/register/vk"
    const val LOGIN_EMAIL = "$BASE_URL/auth/login/email"

    const val GET_NEWS = "$BASE_URL/news"
    const val GET_USER_DATA = "$BASE_URL/user/profile"
    const val UPDATE_USER_INTERESTS = "$BASE_URL/user/interests"

    const val authHeaderName = "Authorization"
}