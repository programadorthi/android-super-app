import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    implementation(project(":applications:norris-facts:domain"))
    implementation(project(":applications:norris-facts:ui"))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    Dependencies.Android.common.forEach { implementation(it) }
}
