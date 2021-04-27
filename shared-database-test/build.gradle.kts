import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testImplementation(project(JavaModules.SHARED_DATABASE_FAKE))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
