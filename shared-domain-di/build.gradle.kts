apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedDomain)
    implementation(libs.kodein.di)
    implementation(libs.kotlinx.coroutines.core)
}
