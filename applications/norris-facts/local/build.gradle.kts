import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":shared-database"))
    api(project(":applications:norris-facts:data"))

    implementation(Dependencies.Kotlin.coroutines)
}
