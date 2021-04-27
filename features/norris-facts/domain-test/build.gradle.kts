import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    testImplementation(project(JavaModules.SHARED_DATABASE_FAKE))
    testImplementation(project(JavaModules.SHARED_DOMAIN_FAKE))
    testImplementation(project(JavaModules.SHARED_NETWORK_FAKE))
    testImplementation(project(JavaModules.Features.NorrisFacts.DOMAIN_FAKE))
    Dependencies.UnitTest.all.forEach { testImplementation(it) }
}
