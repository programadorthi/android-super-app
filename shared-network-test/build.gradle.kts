import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":shared-domain-fake"))
    api(project(":shared-network-fake"))
    testImplementation(Dependencies.Kotlin.serialization)
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
