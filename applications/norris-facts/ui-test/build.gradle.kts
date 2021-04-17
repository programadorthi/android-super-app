import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    testImplementation(project(":shared-ui-fake"))
    testImplementation(project(":applications:norris-facts:domain-fake"))
    testImplementation(project(":applications:norris-facts:ui"))
    testImplementation(project(":applications:norris-facts:ui-fake"))
    testImplementation(Dependencies.Android.lifecycleRuntime)
    testImplementation(Dependencies.UnitTest.robolectric)
    Dependencies.Android.common.forEach { testImplementation(it) }
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
    Dependencies.AndroidTest.all.forEach { testImplementation(it) }
}
