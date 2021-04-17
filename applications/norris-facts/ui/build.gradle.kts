import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("super-module")
}

android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.chucknorris.io/jokes/\"")
        buildConfigField("String", "DATABASE_NAME", "\"norris.db\"")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":shared-database-di-android"))
    implementation(project(":shared-domain-di"))
    implementation(project(":shared-network-di"))
    implementation(project(":shared-retrofit-di"))
    implementation(project(":shared-ui-di"))
    implementation(project(":applications:norris-facts:di"))
    implementation(Dependencies.Android.lifecycleRuntime)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.DI.hiltAndroid)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.retrofit)
    kapt(Dependencies.DI.hiltCompiler)
    Dependencies.Android.common.forEach { implementation(it) }
}
