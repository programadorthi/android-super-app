import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    testApi(project(":shared-test"))
    testApi(project(":shared-ui-fake"))
    testApi(project(":applications:norris-facts:domain-fake"))
    testApi(project(":applications:norris-facts:ui"))
    testImplementation(Dependencies.Android.lifecycleRuntime)
    Dependencies.Android.common.forEach { testImplementation(it) }
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
