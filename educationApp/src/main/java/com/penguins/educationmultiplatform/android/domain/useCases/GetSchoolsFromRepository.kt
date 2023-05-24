package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class GetSchoolsFromRepository() {


    fun invoke(): Flow<List<SchoolDataUi>> {
        return flow {

            val mutableList = mutableListOf<SchoolDataUi>()


            for (i in 0..150) {

                val type = when {
                    i % 3 == 0 -> SchoolType.ARTISTIC
                    i % 4 == 0 -> SchoolType.MUSICAL
                    i % 5 == 0 -> SchoolType.DANCING
                    i % 7 == 0 -> SchoolType.THEATRICAL
                    else -> SchoolType.THEATRICAL
                }

                val school = when(type){
                    SchoolType.ARTISTIC -> "художественн"
                            SchoolType.MUSICAL -> "музыкальн"
                            SchoolType.DANCING -> "танцевальн"
                            SchoolType.THEATRICAL -> "театральн"
                }
                mutableList.add(
                    SchoolDataUi(
                        id = i,
                        type = type,
                        name = "Московская городская детская ${school}ая школа имени С.С.Прокофьева",
                        address = "Токмаков пер., д. 8",
                        description = "Описание московской городской детской ${school}ой школы имени С.С. Прокофьева описание московской городской детской музыкальной школы имени С.С. Прокофьева",
                        phoneNumber = "+7 (499) 261-03-83",
                        email = "dmshprokofiev@culture.mos.ru",
                        coords = Point(
                            Random.nextDouble(55.660000, 55.870000),
                            Random.nextDouble(37.438000, 37.835000)
                        )
                    )
                )
            }

            emit(mutableList)


        }
    }
}