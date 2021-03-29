import dev.programadorthi.dependencies.Dependencies

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
    implementation(Dependencies.Kotlin.plugin)
    implementation(Dependencies.Android.plugin)
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