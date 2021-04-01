import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":applications:norris-facts:domain"))

    implementation(Dependencies.Kotlin.coroutines)
}
