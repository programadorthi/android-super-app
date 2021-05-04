apply(from = "../gradle/android-library.gradle")

plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    api(projects.sharedDatabaseDi)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.kodein.di)
}
