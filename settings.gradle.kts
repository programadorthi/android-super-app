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

include(":app")
include(":shared-database")
include(":shared-database-di")
include(":shared-database-di-android")
include(":shared-database-fake")
include(":shared-database-test")
include(":shared-domain")
include(":shared-domain-di")
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

include(":features:norris-facts:di")
include(":features:norris-facts:domain")
include(":features:norris-facts:domain-fake")
include(":features:norris-facts:domain-impl")
include(":features:norris-facts:domain-test")
include(":features:norris-facts:ui")
include(":features:norris-facts:ui-fake")
include(":features:norris-facts:ui-test")
