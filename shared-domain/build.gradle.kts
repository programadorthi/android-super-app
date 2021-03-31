import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    implementation(Dependencies.Kotlin.serialization)

    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
