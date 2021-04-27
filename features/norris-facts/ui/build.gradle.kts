import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("super-module")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(project(JavaModules.Features.NorrisFacts.DI))
    api(project(LibraryModules.SHARED_UI_DI))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.DI.hiltAndroid)
    kapt(Dependencies.DI.hiltCompiler)
    Dependencies.Android.common.forEach { implementation(it) }
}
