apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(projects.sharedDatabaseFake)
    testImplementation(libs.bundles.unit.test)
}
