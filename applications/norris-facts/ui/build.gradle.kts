import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    implementation(project(":shared-domain"))
    implementation(project(":applications:norris-facts:di"))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.DI.kodeinAndroid)
    Dependencies.Android.common.forEach { implementation(it) }
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
    Dependencies.AndroidTest.all.forEach { androidTestImplementation(it) }
}
