import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    // We need set using complete classpath instead of import the class Version
    kotlin("plugin.serialization") version dev.programadorthi.dependencies.Version.KOTLIN
    id("super-module")
}

dependencies {
    api(project(":shared-network"))
    api(project(":applications:norris-facts:data"))

    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.retrofit)
}
