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
    api(project(JavaModules.SHARED_DATABASE))
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)
}
