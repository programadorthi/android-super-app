import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("super-module")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(project(JavaModules.SHARED_DOMAIN_DI))
    api(project(JavaModules.Features.NorrisFacts.DOMAIN_IMPL))
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.Network.retrofit)
    kapt(Dependencies.DI.hiltCompiler)
}
