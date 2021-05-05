plugins {
    id("com.android.library")
    id("android-project")
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
