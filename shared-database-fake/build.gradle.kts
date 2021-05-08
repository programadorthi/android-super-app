plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedDatabase)
    implementation(libs.sqldelight.sqlite.driver)
}
