plugins {
    id("jvm-project")
}

dependencies {
    testImplementation(projects.sharedDatabaseFake)
    testImplementation(libs.bundles.unit.test)
}
