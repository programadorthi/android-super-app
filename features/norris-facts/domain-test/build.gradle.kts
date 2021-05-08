plugins {
    id("jvm-project")
}

dependencies {
    testImplementation(projects.sharedDatabaseFake)
    testImplementation(projects.sharedDomainFake)
    testImplementation(projects.sharedNetworkFake)
    testImplementation(projects.features.norrisFacts.domainFake)
    testImplementation(libs.bundles.unit.test)
}
