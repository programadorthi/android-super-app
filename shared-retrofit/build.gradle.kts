import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.serializationConverter)
}
