import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("super-module")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(project(JavaModules.SHARED_NETWORK))
    api(project(JavaModules.SHARED_RETROFIT))
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.retrofit)
    kapt(Dependencies.DI.hiltCompiler)
}
