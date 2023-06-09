package com.penguins.educationmultiplatform.android.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.google.android.gms.location.LocationServices
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.DetailCourseViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.SharedViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.VideoItemViewModel
import com.penguins.educationmultiplatform.android.data.location.DefaultLocationTracker
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
import com.penguins.educationmultiplatform.android.domain.location.LocationTracker
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetSchoolsFromRepository
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.tests.GetTestCaseUseCase

import com.penguins.educationmultiplatform.android.domain.useCases.news.GetNewsByCategoryUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.news.GetNewsByParamsUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.news.GetNewsListUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.GetVideoCoursesUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.SaveUserDataUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.UpdateUserInterestsUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.tests.GetUserScoreUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.tests.UpdateUserScoreUseCase

import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.viewModel.HeadingNewsViewModel
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.viewModel.ProfileTabsViewModel
import com.penguins.educationmultiplatform.android.profileScreen.viewModel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { GetVideoCoursesUseCase() }
    single<Player> { ExoPlayer.Builder(androidContext()).build() }

    //viewModels
    viewModel { YandexMapViewModel(get(), get()) }

    viewModel { CategoryViewModel(get(), get()) }
    viewModel { NewsListViewModel(get(), get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { SearchNewsViewModel(get(), get()) }

    viewModel { HeadingNewsViewModel(get(), get()) }
    viewModel { CoursesViewModel(get()) }


    viewModel { ProfileViewModel(get(), get(), get()) }

    viewModel { ProfileTabsViewModel() }

    viewModel { CoursesViewModel(get()) }
    viewModel { DetailCourseViewModel(get(), get()) }
    viewModel { VideoItemViewModel(get(),get(), get()) }

    viewModel { HeadingNewsViewModel(get(), get()) }

    single { SharedViewModel() }

    //location
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single <LocationTracker> { DefaultLocationTracker(get(),androidContext()) }

    //useCases
    single { GetUserDataUseCase(get(), get()) }
    single { SaveUserDataUseCase(get()) }
    single { UpdateUserInterestsUseCase(get(), get()) }
    single { GetUserScoreUseCase(get()) }
    single { UpdateUserScoreUseCase(get()) }

    single { GetSchoolsFromRepository() }
    single <AppNavigation> { DestinationController() }
    single { NewsNavigation() }
    single { GetTestCaseUseCase() }

    single { GetNewsListUseCase(get()) }
    single { GetNewsByCategoryUseCase(get()) }
    single { GetNewsByParamsUseCase(get()) }
}