import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(JavaModules.Features.NorrisFacts.DOMAIN_IMPL))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.Network.retrofit)
}
