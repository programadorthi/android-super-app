plugins {
    id("jvm-project")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.sharedDomainDi)
    api(projects.features.norrisFacts.domainImpl)
    implementation(libs.hilt.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.retrofit)
    kapt(libs.hilt.compiler)
}
