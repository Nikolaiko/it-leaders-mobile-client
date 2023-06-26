plugins {
    id("com.android.application")
    kotlin("android")

    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")  version "1.8.10-1.0.9"
    id("com.google.gms.google-services")
}
android {
    namespace = "com.penguins.educationmultiplatform.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.penguins.educationmultiplatform.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")

    //Koin
    implementation("io.insert-koin:koin-android:3.3.2")
    implementation("io.insert-koin:koin-androidx-navigation:3.3.2")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")

    // Coil
    implementation ("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("com.vanniktech:android-image-cropper:4.5.0")

    //VK
    implementation ("com.vk:android-sdk-core:4.0.1")
    implementation ("com.vk:android-sdk-api:4.0.1")

    //Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-insets:0.30.1")
    implementation ("com.google.accompanist:accompanist-coil:0.13.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")

    // YandexMap
    implementation ("com.yandex.android:maps.mobile:4.3.1-lite")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    //Navigation
    val nav_version = "1.8.41-beta"
    implementation("io.github.raamcosta.compose-destinations:core:$nav_version")
    ksp("io.github.raamcosta.compose-destinations:ksp:$nav_version")

    //LocalStorage
    implementation("androidx.preference:preference-ktx:1.2.0")

    // For media playback using ExoPlayer
    val media3_version = "1.0.1"
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    implementation("androidx.media3:media3-exoplayer-dash:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:30.0.1"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-database-ktx:20.0.5")
    implementation ("com.google.firebase:firebase-messaging:23.0.5")
    implementation ("com.google.firebase:firebase-storage-ktx")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.1")

    //Serializaton
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

}