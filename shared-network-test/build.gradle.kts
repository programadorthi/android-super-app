import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testImplementation(project(JavaModules.SHARED_DOMAIN_FAKE))
    testImplementation(project(JavaModules.SHARED_NETWORK_FAKE))
    testImplementation(Dependencies.Kotlin.serialization)
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
