apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.sqldelight.sqlite.driver)
}
