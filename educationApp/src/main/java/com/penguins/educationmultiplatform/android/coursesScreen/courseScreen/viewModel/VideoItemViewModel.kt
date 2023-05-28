package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.google.firebase.storage.FirebaseStorage
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.LessonUiState
import com.penguins.educationmultiplatform.android.domain.useCases.GetVideoCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class VideoItemViewModel(
    val getVideoCoursesUseCase: GetVideoCoursesUseCase,
    val player: Player,
    val sharedViewModel: SharedViewModel
) : ViewModel() {

    private val _lessonState = MutableStateFlow(LessonUiState())
    val lessonState = _lessonState.asStateFlow()

    init {
        player.prepare()
        player.playWhenReady = true
        getVideoCoursesUseCase.invoke().onEach { videoCourses ->
            _lessonState.emit(LessonUiState(loading = true))
            sharedViewModel.idCourse?.let { idCourse ->
                sharedViewModel.idLesson?.let { idLesson ->

                    val uriRef = FirebaseStorage.getInstance(FIREBASE_STORAGE)
                    val uri =
                        uriRef.reference.child(videoCourses[idCourse].listVideo[idLesson].videoName).downloadUrl.await()
                    _lessonState.emit(
                        _lessonState.value.copy(
                            loading = false,
                            selectedLesson = videoCourses[idCourse].listVideo[idLesson],
                            lessons = videoCourses[idCourse].listVideo,
                            nameSchool = videoCourses[idCourse].name,
                            type = videoCourses[idCourse].type
                        )
                    )
                    player.setMediaItem(MediaItem.fromUri(uri))
                }
            }
        }.launchIn(viewModelScope)
    }


    fun selectVideoLesson(id:Int){
        viewModelScope.launch {
            _lessonState.emit(_lessonState.value.copy(selectedLesson = _lessonState.value.lessons[id], loading = true))
            val uriRef = FirebaseStorage.getInstance(FIREBASE_STORAGE)
            _lessonState.value.selectedLesson?.let {
                val uri =
                    uriRef.reference.child(it.videoName).downloadUrl.await()
                player.setMediaItem(MediaItem.fromUri(uri))
            }
            _lessonState.emit(_lessonState.value.copy( loading = false))
        }
    }





    override fun onCleared() {
        super.onCleared()
        player.stop()
    }
}