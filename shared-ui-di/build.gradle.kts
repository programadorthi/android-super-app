plugins {
    id("com.android.library")
    id("android-project")
}

dependencies {
    api(projects.sharedUi)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kodein.di)
    implementation(libs.kodein.android)
    implementation(libs.bundles.android.common)
}
