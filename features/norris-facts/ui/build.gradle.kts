plugins {
    id("com.android.library")
    id("android-project")
    // Gradle plugins are declared on buildSrc
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.features.norrisFacts.di)
    api(projects.sharedUiDi)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.bundles.android.common)
    kapt(libs.hilt.compiler)
}
