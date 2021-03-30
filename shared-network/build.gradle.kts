import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    // We need set using complete classpath instead of import the class Version
    kotlin("plugin.serialization") version dev.programadorthi.dependencies.Version.KOTLIN
    id("super-module")
}

dependencies {
    implementation(project(":shared-domain"))

    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)

    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.serializationConverter)

    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
