package com.penguins.educationmultiplatform.android.mapScreen.data

import com.yandex.mapkit.geometry.Point

data class SchoolDataUi(
    val id:Int,
    val type:SchoolType,
    val name: String,
    val address: String,
    val description:String,
    val phoneNumber: String,
    val email: String,
    val coords:Point
)
