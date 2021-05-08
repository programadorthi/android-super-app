plugins {
    id("jvm-project")
}

dependencies {
    testImplementation(projects.sharedDomainFake)
    testImplementation(libs.kotlinx.serialization)
    testImplementation(libs.bundles.unit.test)
}
