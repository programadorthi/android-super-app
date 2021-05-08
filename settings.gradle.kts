// Enable Gradle's Type-safe dependency accessors
// https://docs.gradle.org/7.0/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
// Enable Gradle's version catalog support
// https://docs.gradle.org/current/userguide/platforms.html
enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "super-app"

include(
    ":app",
    ":shared-database",
    ":shared-database-di",
    ":shared-database-di-android",
    ":shared-database-fake",
    ":shared-database-test",
    ":shared-domain",
    ":shared-domain-di",
    ":shared-domain-fake",
    ":shared-domain-test",
    ":shared-network",
    ":shared-network-di",
    ":shared-network-fake",
    ":shared-network-test",
    ":shared-retrofit",
    ":shared-retrofit-di",
    ":shared-ui",
    ":shared-ui-di"
)
include(":features:norris-facts:di")
include(":features:norris-facts:domain")
include(":features:norris-facts:domain-fake")
include(":features:norris-facts:domain-impl")
include(":features:norris-facts:domain-test")
include(":features:norris-facts:ui")
include(":features:norris-facts:ui-fake")
include(":features:norris-facts:ui-test")
