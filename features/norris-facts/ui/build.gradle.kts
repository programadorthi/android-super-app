apply(from = "../../../gradle/android-library.gradle")

plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    api(projects.features.norrisFacts.di)
    api(projects.sharedUiDi)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.kodein.di)
    implementation(libs.kodein.android)
    implementation(libs.bundles.android.common)
}
