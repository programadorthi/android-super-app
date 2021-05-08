plugins {
    kotlin("kapt")
    id("jvm-project")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
