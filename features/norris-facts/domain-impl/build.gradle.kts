apply(from = "../../../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
    // We need set using complete classpath instead of import the class Version
    kotlin("plugin.serialization") version "1.4.31"
}

dependencies {
    api(projects.sharedDatabase)
    api(projects.sharedNetwork)
    api(projects.features.norrisFacts.domain)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
}
