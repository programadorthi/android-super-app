import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(JavaModules.SHARED_DOMAIN))
    implementation(Dependencies.Kotlin.coroutines)
}
