plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
//    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.penguins.educationmultiplatform.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.penguins.educationmultiplatform.android"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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


    //Ktor
    val ktor_version = "1.6.3"
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    //VK
    implementation ("com.vk:android-sdk-core:4.0.1")
    implementation ("com.vk:android-sdk-api:4.0.1")

    //Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-insets:0.30.1")
    implementation ("com.google.accompanist:accompanist-coil:0.13.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")
//    YandexMap
    implementation ("com.yandex.android:maps.mobile:4.3.1-lite")
    // GMS - Google Mobile Services
//    implementation("com.google.android.gms:play-services-location:21.0.1")
    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

}