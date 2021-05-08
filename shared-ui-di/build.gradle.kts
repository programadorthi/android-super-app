plugins {
    id("com.android.library")
    kotlin("kapt")
    id("android-project")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedUi)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.bundles.android.common)
    kapt(libs.hilt.compiler)
}
