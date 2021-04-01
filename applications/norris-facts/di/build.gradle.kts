import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":applications:norris-facts:local"))
    api(project(":applications:norris-facts:remote"))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.Network.retrofit)
}
