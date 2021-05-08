plugins {
    id("com.android.library")
    kotlin("kapt")
    id("android-project")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDatabaseDi)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
