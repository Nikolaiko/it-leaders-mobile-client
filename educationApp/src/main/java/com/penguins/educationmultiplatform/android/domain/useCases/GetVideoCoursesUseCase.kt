package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoCourse
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoLesson
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVideoCoursesUseCase {

    fun invoke(): Flow<List<VideoCourse>> {
        return flow {

            val listCourses = mutableListOf<VideoCourse>()
            for (i in 0..14) {
                val listVideo = mutableListOf<VideoLesson>()
                when {
                    i < 3 -> {
                        var j = 0
                        repeat(3) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.MUSICAL.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.MUSICAL.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "music ${j + 1}.mp4",
//                                    uriRef.reference.child("music ${i + 1}.mp4").downloadUrl.await(),
                                    nameSchool = SchoolType.MUSICAL.nameSchool,
                                    id = j
                                )
                            )
                            j++
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.MUSICAL.nameSchool}",
                                SchoolType.MUSICAL,
                                listVideo
                            )
                        )
                    }
                    i in 3..6 -> {
                        var j = 0
                        repeat(4) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.DANCING.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.DANCING.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "dance ${j + 1}.mp4",
                                    nameSchool = SchoolType.DANCING.nameSchool,
                                    id = j
                                )
                            )
                            j++
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.DANCING.nameSchool}",
                                SchoolType.DANCING,
                                listVideo
                            )
                        )
                    }
                    i in 7..10 -> {

                        var j = 0
                        repeat(4) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.ARTISTIC.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.ARTISTIC.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "artist ${j + 1}.mp4",
                                    nameSchool = SchoolType.ARTISTIC.nameSchool,
                                    id = j
                                )
                            )
                            j++
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.ARTISTIC.nameSchool}",
                                SchoolType.ARTISTIC,
                                listVideo
                            )
                        )

                    }
                    i in 11..14 -> {
                        var j = 0

                        repeat(4) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.THEATRICAL.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.THEATRICAL.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "theater ${j + 1}.mp4",
                                    nameSchool = SchoolType.THEATRICAL.nameSchool,
                                    id = j
                                )
                            )
                            j++
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.THEATRICAL.nameSchool}",
                                SchoolType.THEATRICAL,
                                listVideo
                            )
                        )

                    }
                }
            }
            emit(listCourses)
        }
    }

}