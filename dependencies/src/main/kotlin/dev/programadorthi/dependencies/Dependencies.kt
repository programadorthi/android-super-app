package dev.programadorthi.dependencies

object Dependencies {
    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
    }

    object Android {
        private const val activityKtx = "androidx.activity:activity-ktx:1.2.2"
        private const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        private const val coreKtx = "androidx.core:core-ktx:1.3.2"
        private const val material = "com.google.android.material:material:1.3.0"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0-rc01"
        const val sqldelightDriver = "com.squareup.sqldelight:android-driver:1.4.4"

        val common = listOf(activityKtx, appCompat, coreKtx, material)
    }

    object Network {
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object DI {
        const val kodein = "org.kodein.di:kodein-di:7.5.0"
        const val kodeinAndroid = "org.kodein.di:kodein-di-framework-android-x:7.5.0"
    }

    object AndroidTest {
        val all = listOf(
            "androidx.test.ext:junit:1.1.2",
            "androidx.test.espresso:espresso-core:3.3.0"
        )
    }

    object UnitTest {
        val all = listOf(
            "junit:junit:4.13.2",
            "org.assertj:assertj-core:3.19.0",
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
        )
    }
}
