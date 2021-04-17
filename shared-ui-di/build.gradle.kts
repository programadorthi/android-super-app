import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("super-module")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(project(":shared-ui"))
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.DI.hiltAndroid)
    Dependencies.Android.common.forEach { implementation(it) }
    kapt(Dependencies.DI.hiltCompiler)
}
