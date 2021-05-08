plugins {
    id("jvm-project")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedNetwork)
    api(projects.sharedRetrofit)
    implementation(libs.hilt.core)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    kapt(libs.hilt.compiler)
}
