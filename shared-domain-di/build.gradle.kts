plugins {
    id("jvm-project")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDomain)
    implementation(libs.hilt.core)
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.compiler)
}
