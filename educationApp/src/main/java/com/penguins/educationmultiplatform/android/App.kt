package com.penguins.educationmultiplatform.android

import android.app.Application
import com.penguins.educationmultiplatform.android.di.androidModule
import com.penguins.educationmultiplatform.android.di.authModule
import com.penguins.educationmultiplatform.android.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class App:Application() {

        override fun onCreate() {
            super.onCreate()

            GlobalContext.startKoin {
                androidLogger()
                androidContext(this@App)

                modules(
                    repositoriesModule,
                    authModule,
                    androidModule
                )
            }
        }

}