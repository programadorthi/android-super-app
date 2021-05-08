plugins {
    id("jvm-project")
    // We need set using complete classpath instead of import the class Version
    kotlin("plugin.serialization")
}

dependencies {
    api(projects.sharedDatabase)
    api(projects.sharedNetwork)
    api(projects.features.norrisFacts.domain)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
}
