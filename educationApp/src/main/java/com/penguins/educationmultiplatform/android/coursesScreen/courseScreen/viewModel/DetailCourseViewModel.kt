package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model.VideoCourse
import com.penguins.educationmultiplatform.android.domain.useCases.GetVideoCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailCourseViewModel(
    private val getVideoCoursesUseCase: GetVideoCoursesUseCase,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private val _state = MutableStateFlow<List<VideoCourse>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getVideoCoursesUseCase.invoke().onEach {
            _state.emit(it)
        }.launchIn(viewModelScope)
    }

    fun setSelectedCourse(id:Int){
        sharedViewModel.idCourse = id
    }
    fun setSelectedLesson(id:Int){
        sharedViewModel.idLesson = id
    }





}