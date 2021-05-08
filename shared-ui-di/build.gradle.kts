plugins {
    id("com.android.library")
    id("android-project")
    kotlin("kapt")
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
