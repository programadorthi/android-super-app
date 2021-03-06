plugins {
    id("com.android.library")
    id("android-project")
}

dependencies {
    testImplementation(projects.features.norrisFacts.domainFake)
    testImplementation(projects.features.norrisFacts.ui)
    testImplementation(projects.features.norrisFacts.uiFake)
    testImplementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.robolectric)
    testImplementation(libs.bundles.android.common)
    testImplementation(libs.bundles.unit.test)
    testImplementation(libs.bundles.android.test)
}
