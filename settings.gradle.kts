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

include(":shared-database")
include(":shared-domain")
include(":shared-domain-fake")
include(":shared-domain-test")
include(":shared-network")
include(":shared-network-fake")
include(":shared-network-test")
include(":shared-retrofit")

include(":applications:norris-facts:data")
include(":applications:norris-facts:di")
include(":applications:norris-facts:domain")
include(":applications:norris-facts:local")
include(":applications:norris-facts:remote")
include(":applications:norris-facts:ui")
