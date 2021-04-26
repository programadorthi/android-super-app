import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":features:norris-facts:domain-impl"))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.Network.retrofit)
}
