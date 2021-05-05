apply(from = "../../../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(projects.sharedDatabaseFake)
    testImplementation(projects.sharedDomainFake)
    testImplementation(projects.sharedNetworkFake)
    testImplementation(projects.features.norrisFacts.domainFake)
    testImplementation(libs.bundles.unit.test)
}
