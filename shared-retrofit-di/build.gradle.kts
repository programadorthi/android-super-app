import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(JavaModules.SHARED_NETWORK))
    api(project(JavaModules.SHARED_RETROFIT))
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.retrofit)
}
