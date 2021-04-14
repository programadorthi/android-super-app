import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testImplementation(project(":shared-database-fake"))
    testImplementation(project(":shared-network-fake"))
    testImplementation(project(":applications:norris-facts:domain-fake"))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
