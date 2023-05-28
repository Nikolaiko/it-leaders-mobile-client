package com.penguins.educationmultiplatform.android.data.model.dto.profile

import android.icu.util.Calendar
import com.penguins.educationmultiplatform.android.data.model.consts.birthDateFormat

@kotlinx.serialization.Serializable
data class VKProfile(
    val response: Response
) {
    fun formatBirthdate(): String {
        val split = response.bdate.split(".")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, split[2].toInt())
        calendar.set(Calendar.MONTH, split[1].toInt())
        calendar.set(Calendar.DAY_OF_MONTH, split[0].toInt())
        return birthDateFormat.format(calendar.time)
    }
}