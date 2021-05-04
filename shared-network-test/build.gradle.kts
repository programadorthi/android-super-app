apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(projects.sharedDomainFake)
    testImplementation(projects.sharedNetworkFake)
    implementation(libs.kotlinx.serialization)
    testImplementation(libs.bundles.unit.test)
}
