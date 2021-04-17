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
    api(project(":shared-domain-di"))
    api(project(":applications:norris-facts:domain-impl"))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.Network.retrofit)
    kapt(Dependencies.DI.hiltCompiler)
}
