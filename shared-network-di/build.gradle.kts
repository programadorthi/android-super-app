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
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)
}
