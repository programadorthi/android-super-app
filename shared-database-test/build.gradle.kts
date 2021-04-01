import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testApi(project(":shared-database-fake"))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
