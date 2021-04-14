import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testImplementation(project(":shared-domain-fake"))
    testImplementation(project(":shared-network-fake"))
    testImplementation(Dependencies.Kotlin.serialization)
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
