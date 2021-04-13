import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    api(project(":applications:norris-facts:ui"))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    Dependencies.Android.common.forEach { implementation(it) }
}
