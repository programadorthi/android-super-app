import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":shared-network"))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.kodein)
}
