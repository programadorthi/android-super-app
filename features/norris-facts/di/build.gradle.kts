apply(from = "../../../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.features.norrisFacts.domainImpl)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kodein.di)
    implementation(libs.retrofit)
}
