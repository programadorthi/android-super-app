plugins {
    id("jvm-project")
}

dependencies {
    api(projects.features.norrisFacts.domainImpl)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kodein.di)
    implementation(libs.retrofit)
}
