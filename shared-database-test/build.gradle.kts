apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(projects.sharedDatabaseFake)
    testImplementation(libs.bundles.unit.test)
}
