plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

// To make it available as direct dependency
group = "dev.programadorthi.dependencies"
version = "0.0.1"

repositories {
    jcenter()
}

gradlePlugin {
    plugins.register("dependencies") {
        id = "dependencies"
        implementationClass = "dev.programadorthi.dependencies.DependenciesPlugin"
    }
}