import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":shared-domain"))
    implementation(Dependencies.Kotlin.coroutines)
}
