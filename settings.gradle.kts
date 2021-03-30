pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }
}

rootProject.name = "Super App"

includeBuild("dependencies")
includeBuild("configurations")

include(":shared-domain")
include(":shared-network")

include(":applications:norris-facts:domain")
