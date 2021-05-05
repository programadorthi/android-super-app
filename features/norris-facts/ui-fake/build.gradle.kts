plugins {
    id("com.android.library")
    id("android-project")
}

dependencies {
    implementation(projects.features.norrisFacts.domain)
    implementation(projects.features.norrisFacts.ui)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.bundles.android.common)
}
