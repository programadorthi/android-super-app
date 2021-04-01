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
include(":shared-database-di")
include(":shared-database-fake")
include(":shared-database-test")
include(":shared-domain")
include(":shared-domain-fake")
include(":shared-domain-test")
include(":shared-network")
include(":shared-network-di")
include(":shared-network-fake")
include(":shared-network-test")
include(":shared-retrofit")

include(":applications:norris-facts:di")
include(":applications:norris-facts:domain")
include(":applications:norris-facts:domain-fake")
include(":applications:norris-facts:domain-impl")
include(":applications:norris-facts:domain-test")
include(":applications:norris-facts:ui")
