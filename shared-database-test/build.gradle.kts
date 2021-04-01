import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":shared-database-fake"))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
