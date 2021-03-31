import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    implementation(project(":shared-domain"))
    implementation(project(":applications:norris-facts:domain"))
    implementation(project(":applications:norris-facts:local"))
    implementation(project(":applications:norris-facts:remote"))

    implementation(Dependencies.Kotlin.coroutines)
}
