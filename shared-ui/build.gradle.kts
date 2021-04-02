import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    api(project(":shared-domain"))
    implementation(Dependencies.Android.lifecycleRuntime)
    Dependencies.Android.common.forEach { implementation(it) }
}