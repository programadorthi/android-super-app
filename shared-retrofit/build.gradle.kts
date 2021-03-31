import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    implementation(project(":shared-network"))

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.serializationConverter)
}
