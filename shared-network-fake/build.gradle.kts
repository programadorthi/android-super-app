apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedNetwork)
    implementation(libs.kotlinx.coroutines.core)
}
