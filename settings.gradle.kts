import io.labs.dotanuki.magicmodules.MagicModulesExtension

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }
}

rootProject.name = "Super App"

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath("com.github.programadorthi:magic-modules:0.0.6")
    }
}

apply(plugin = "io.labs.dotanuki.magicmodules")

configure<MagicModulesExtension> {
    includeBuildDir = "dependencies"
    maxDepthToBuildScript = 4
    modulesToSkip = listOf(":configurations")
}

includeBuild("dependencies")
includeBuild("configurations")
