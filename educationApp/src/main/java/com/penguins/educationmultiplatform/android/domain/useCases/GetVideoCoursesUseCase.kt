package com.penguins.educationmultiplatform.android.domain.useCases

import com.google.firebase.storage.FirebaseStorage
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoCourse
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoLesson
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.FIREBASE_STORAGE
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVideoCoursesUseCase {

    fun invoke(): Flow<List<VideoCourse>> {
        return flow {

            val listCourses = mutableListOf<VideoCourse>()
            val uriRef = FirebaseStorage.getInstance(FIREBASE_STORAGE)
            for (i in 0..11) {
                val listVideo = mutableListOf<VideoLesson>()
                when {
                    i < 3 -> {
                        repeat(3) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.MUSICAL.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.MUSICAL.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "music ${i + 1}.mp4",
//                                    uriRef.reference.child("music ${i + 1}.mp4").downloadUrl.await(),
                                    nameSchool = SchoolType.MUSICAL.nameSchool
                                )
                            )
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.MUSICAL.nameSchool}",
                                SchoolType.MUSICAL,
                                listVideo
                            )
                        )
                    }
                    i in 3..5 -> {

                        repeat(3) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.DANCING.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.DANCING.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "music ${i - 3 + 1}.mp4",
                                    nameSchool = SchoolType.DANCING.nameSchool
                                )
                            )
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.DANCING.nameSchool}",
                                SchoolType.DANCING,
                                listVideo
                            )
                        )
                    }
                    i in 6..8 -> {

                        repeat(3) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.ARTISTIC.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.ARTISTIC.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "music ${i - 6 + 1}.mp4",
                                    nameSchool = SchoolType.ARTISTIC.nameSchool
                                )
                            )
                        }
                        listCourses.add(
                            VideoCourse(
                                name = "Курс №${i + 1} от ${SchoolType.ARTISTIC.nameSchool}",
                                SchoolType.ARTISTIC,
                                listVideo
                            )
                        )

                    }
                    i in 10..11 -> {

                        repeat(3) {
                            listVideo.add(
                                VideoLesson(
                                    name = "урок курса от ${SchoolType.THEATRICAL.nameSchool}",
                                    description = "Описание урока для курса от: ${SchoolType.THEATRICAL.nameSchool} ",
                                    nameTeacher = "Величевский Валерий Анатольевич",
                                    graduation = "Заведующий кафедой хорового искусства",
                                    videoName = "music ${i - 10 + 1}.mp4",
                                    nameSchool = SchoolType.THEATRICAL.nameSchool
                                )
                            )
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