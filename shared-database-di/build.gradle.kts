plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.kodein.di)
}
