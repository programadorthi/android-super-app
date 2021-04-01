import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testApi(project(":shared-domain-fake"))
    testApi(project(":shared-network-fake"))
    testImplementation(Dependencies.Kotlin.serialization)
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
