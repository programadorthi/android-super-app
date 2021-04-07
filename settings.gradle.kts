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
include(":shared-database-di-android")
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
include(":shared-retrofit-di")
include(":shared-ui")
include(":shared-ui-di")
include(":shared-ui-fake")

include(":applications:norris-facts:di")
include(":applications:norris-facts:domain")
include(":applications:norris-facts:domain-fake")
include(":applications:norris-facts:domain-impl")
include(":applications:norris-facts:domain-test")
include(":applications:norris-facts:ui")
include(":applications:norris-facts:ui-fake")
include(":applications:norris-facts:ui-test")
