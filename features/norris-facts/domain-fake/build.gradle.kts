plugins {
    id("jvm-project")
}

dependencies {
    // TODO: fake project has impl dependency is WRONG. Still here because FactsService is a retrofit interface inside impl module only
    api(projects.features.norrisFacts.domainImpl)
    implementation(libs.kotlinx.coroutines.core)
}
