apply(from = "../gradle/android-library.gradle")

plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    api(projects.sharedUi)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kodein.di)
    implementation(libs.kodein.android)
    implementation(libs.bundles.android.common)
}
