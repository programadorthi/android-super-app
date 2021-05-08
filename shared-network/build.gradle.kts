plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedDomain)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)

    testImplementation(libs.bundles.unit.test)
}
