plugins {
    id("com.android.library")
    id("android-project")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDatabaseDi)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
