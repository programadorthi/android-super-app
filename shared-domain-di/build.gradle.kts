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
    api(project(":shared-domain"))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)
}
