import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    implementation(project(JavaModules.Features.NorrisFacts.DOMAIN))
    implementation(project(LibraryModules.Features.NorrisFacts.UI))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    Dependencies.Android.common.forEach { implementation(it) }
}
