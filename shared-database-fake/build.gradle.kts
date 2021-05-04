apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.sqldelight.sqlite.driver)
}
