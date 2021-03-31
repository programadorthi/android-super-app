import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    implementation(project(":shared-domain"))

    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)

    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
