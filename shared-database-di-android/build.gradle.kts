plugins {
    id("com.android.library")
    id("android-project")
}

dependencies {
    api(projects.sharedDatabaseDi)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.kodein.di)
}
