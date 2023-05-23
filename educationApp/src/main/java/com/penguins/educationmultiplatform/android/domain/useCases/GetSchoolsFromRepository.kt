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


            var type = 0
            for (i in 0..150) {
                if (i % 5 == 0)
                    type++

                mutableList.add(
                    SchoolDataUi(
                        id = i,
                        type = when (type) {
                            1 -> SchoolType.ARTISTIC
                            2 -> SchoolType.MUSICAL
                            3 -> SchoolType.DANCING
                            4 -> SchoolType.THEATRICAL
                            else -> SchoolType.THEATRICAL
                        },
                        name = "Московская городская детская музыкальная школа имени С.С.Прокофьева",
                        address = "Токмаков пер., д. 8",
                        description = "Описание московской городской детской музыкальной школы имени С.С. Прокофьева описание московской городской детской музыкальной школы имени С.С. Прокофьева",
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