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
    api(project(":shared-network"))
    api(project(":shared-retrofit"))
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.retrofit)
    kapt(Dependencies.DI.hiltCompiler)
}
