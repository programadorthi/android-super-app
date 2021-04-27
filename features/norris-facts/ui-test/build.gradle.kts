import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    testImplementation(project(JavaModules.Features.NorrisFacts.DOMAIN_FAKE))
    testImplementation(project(LibraryModules.Features.NorrisFacts.UI))
    testImplementation(project(LibraryModules.Features.NorrisFacts.UI_FAKE))
    testImplementation(Dependencies.Android.lifecycleRuntime)
    testImplementation(Dependencies.UnitTest.robolectric)
    Dependencies.Android.common.forEach { testImplementation(it) }
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
    Dependencies.AndroidTest.all.forEach { testImplementation(it) }
}
