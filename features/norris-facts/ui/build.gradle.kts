import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    /*implementation(project(":shared-database-di-android"))
    implementation(project(":shared-domain-di"))
    implementation(project(":shared-network-di"))
    implementation(project(":shared-retrofit-di"))*/
    implementation(project(":shared-ui-di"))
    implementation(project(":features:norris-facts:di"))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.DI.kodeinAndroid)
    Dependencies.Android.common.forEach { implementation(it) }
}
