plugins {
    id("com.android.library")
    id("android-project")
}

dependencies {
    api(projects.sharedDomain)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.bundles.android.common)
}
