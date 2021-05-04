apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedDomain)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)

    testImplementation(libs.bundles.unit.test)
}
