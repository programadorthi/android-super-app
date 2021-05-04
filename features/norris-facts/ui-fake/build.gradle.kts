apply(from = "../../../gradle/android-library.gradle")

plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(projects.features.norrisFacts.domain)
    implementation(projects.features.norrisFacts.ui)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.bundles.android.common)
}
