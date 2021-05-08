plugins {
    id("jvm-project")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
