import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testApi(project(":shared-database-fake"))
    testApi(project(":shared-network-fake"))
    testApi(project(":applications:norris-facts:domain-fake"))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
