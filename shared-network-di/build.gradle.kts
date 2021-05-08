plugins {
    kotlin("kapt")
    id("jvm-project")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDomainDi)
    api(projects.sharedNetwork)
    implementation(libs.hilt.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
    kapt(libs.hilt.compiler)
}
