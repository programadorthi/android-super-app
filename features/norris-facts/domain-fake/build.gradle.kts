import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    // TODO: fake project has impl dependency is WRONG. Still here because FactsService is a retrofit interface inside impl module only
    api(project(JavaModules.Features.NorrisFacts.DOMAIN_IMPL))
    implementation(Dependencies.Kotlin.coroutines)
}
