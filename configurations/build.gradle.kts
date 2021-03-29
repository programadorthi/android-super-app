import dev.programadorthi.dependencies.BuildPlugins

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("dependencies")
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation(BuildPlugins.android)
    implementation(BuildPlugins.kotlin)
    implementation(BuildPlugins.ktlint)
    implementation(BuildPlugins.testLogger)
}

kotlin.sourceSets.getByName("main").kotlin.srcDir("../dependencies/src/main/kotlin")

gradlePlugin {
    plugins.register("configurations") {
        id = "configurations"
        implementationClass = "dev.programadorthi.configurations.ConfigurationsPlugin"
    }
    plugins.register("project") {
        id = "project"
        implementationClass = "dev.programadorthi.configurations.ProjectPlugin"
    }
}
