import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    testApi(project(":shared-ui-fake"))
    testApi(project(":applications:norris-facts:domain-fake"))
    testApi(project(":applications:norris-facts:ui-fake"))
    testImplementation(Dependencies.Android.lifecycleRuntime)
    testImplementation(Dependencies.UnitTest.robolectric)
    Dependencies.Android.common.forEach { testImplementation(it) }
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
    Dependencies.AndroidTest.all.forEach { testImplementation(it) }
}
